package com.leone.tx.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-14
 **/
public class RedissonLockTest {

    public static void main(String[] args) {
        // redis 连接配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://ip:6379");
        RedissonClient redisson = Redisson.create(config);

        // 获得锁对象实例
        RLock testLock = redisson.getLock("testLock");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Task("worker_" + i, testLock));
        }
        fixedThreadPool.shutdown();
    }

    static class Task implements Runnable {
        private String name;
        private RLock rLock;

        public Task(String name, RLock rLock) {
            this.name = name;
            this.rLock = rLock;
        }

        public void run() {
            boolean res = false;
            try {
                System.out.println(name + "尝试获取锁...");
                // 1.不支持过期自动解锁，不会超时
                // rLock.lock();

                // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
                // lock.lock(10, TimeUnit.SECONDS);

                // 3. 尝试加锁，最多等待20秒，上锁以后10秒自动解锁（实际项目中推荐这种，以防出现死锁）
                res = rLock.tryLock(20, 10, TimeUnit.SECONDS);

                System.err.println(name + " 获取锁...");
                if (res) {
                    System.err.println(name + " exec success...");
                    Thread.sleep(2000);
                } else {
                    System.err.println(name + " 等待超时...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (res) {
                    System.err.println(name + " 释放锁...");
                    rLock.unlock();
                }
            }
        }
    }


}
