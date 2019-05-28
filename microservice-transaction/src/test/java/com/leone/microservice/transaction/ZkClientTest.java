package com.leone.microservice.transaction;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
public class ZkClientTest {

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("ip:2181")
            .sessionTimeoutMs(5000)
            .connectionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .namespace("test")
            .build();

    @Before
    public void init() {
        client.start();
    }

    @Test
    public void create() throws Exception {
        client.create().forPath("/bbb", "hello world".getBytes());
        // client.create().forPath("path");
        // client.create().withMode(CreateMode.EPHEMERAL).forPath("path");
        // client.create().withMode(CreateMode.EPHEMERAL).forPath("bbb","init".getBytes());
    }

    @Test
    public void select() throws Exception {
        byte[] bytes = client.getData().forPath("/bbb");
        System.out.println(new String(bytes));
    }

    @Test
    public void update() throws Exception {
        client.setData().forPath("/bbb", "world".getBytes());
    }

    @Test
    public void delete() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath("/test");
        // client.delete().forPath("/bbb");
    }


}
