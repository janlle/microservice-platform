package com.leone.microservice.transaction.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-14
 **/
@Configuration
public class ZkConfig {

    @Value("${app.zk.url}")
    private String zkUrl;

    @Value("${app.zk.lockPath}")
    private String lockPath;

    /**
     * zookeeper 连接客户端
     *
     * @return
     */
    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        return client;
    }

    /**
     * zookeeper 锁
     *
     * @return
     */
    @Bean
    public InterProcessMutex lock() throws Exception{
        CuratorFramework client = curatorFramework();
        if (client.checkExists().forPath(lockPath) == null) {
            client.create().creatingParentsIfNeeded().forPath(lockPath);
        }
        return new InterProcessMutex(client, lockPath);
    }

}