package com.leone.microservice.transaction.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
public class ZkClient {

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("ip:2181")
            .sessionTimeoutMs(5000)
            .connectionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .namespace("test")
            .build();


    public static void main(String[] args) throws Exception {
        client.start();
        create();
    }

    public static void create() throws Exception {
        client.create().forPath("/bbb", "hello world".getBytes());
        // client.create().forPath("path");
        // client.create().withMode(CreateMode.EPHEMERAL).forPath("path");
        // client.create().withMode(CreateMode.EPHEMERAL).forPath("bbb","init".getBytes());
    }

    public static void select() throws Exception {
        byte[] bytes = client.getData().forPath("/bbb");
        System.out.println(new String(bytes));
    }

    public static void update() throws Exception {
        client.setData().forPath("/bbb", "world".getBytes());
    }

    public static void delete() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath("/test");
        // client.delete().forPath("/bbb");
    }


}
