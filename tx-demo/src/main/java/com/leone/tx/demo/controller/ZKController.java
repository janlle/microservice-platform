package com.leone.tx.demo.controller;

import com.leone.tx.demo.zookeeper.TxZkLock;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zk")
public class ZKController {

    @Autowired
    private CuratorFramework zkClient;

    @Autowired
    private TxZkLock txZkLock;

    private String url = "127.0.0.1:2181";

    private int timeout = 3000;

    private String lockPath = "/lock";

    private int k = 1;

    @GetMapping("/lock")
    public Boolean getLock() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                txZkLock.lock();

                txZkLock.unlock();
            }).start();
        }
        return true;
    }
}