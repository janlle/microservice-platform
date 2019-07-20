package com.leone.microservice.lock.zookeeper;

import com.leone.microservice.lock.Lock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-18
 **/
@Slf4j
@Component
public class ZkLock implements Lock {

    private static final String CONNECT_ADDR = "39.108.125.41:2181";

    // session超时时间ms
    private static final int SESSION_TIMEOUT = 16000;

    private static CuratorFramework client;

    private InterProcessMutex interProcessMutex;

    @PostConstruct
    public void initZkLock() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        interProcessMutex = new InterProcessMutex(client, "/lock");
        log.info("zookeeper lock init successful...");
    }

    @Override
    public void lock() throws Exception {
        interProcessMutex.acquire();
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public void unlock() throws Exception {
        interProcessMutex.release();
    }
}
