package com.leone.tx.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryOneTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
public class ZookeeperLockTest {

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("ip:2181", new RetryOneTime(3000));
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/m-lock");

        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Task("worker_" + i, lock));
        }
        pool.shutdown();
    }

    static class Task implements Runnable {
        private String name;
        private InterProcessMutex lock;

        public Task(String name, InterProcessMutex lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " 尝试获取锁...");

                lock.acquire();
                System.err.println(name + " 获取锁...");

                TimeUnit.MILLISECONDS.sleep(3000);
                System.err.println(name + " exec success...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                    System.err.println(name + " 释放锁...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
