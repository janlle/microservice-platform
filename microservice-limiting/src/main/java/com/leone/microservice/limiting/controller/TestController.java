package com.leone.microservice.limiting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author leone
 * @since 2018-04-22
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private static Map<Integer, Boolean> data = new HashMap<>();

    Stack stack = new Stack();

    {
        for (int i = 0; i < 10000; i++) {
            data.put(i, true);
            stack.push(i);
        }
        log.info("初始化map...");
    }

    private int a = 0;

    @ResponseBody
    @RequestMapping("/test")
    public void test() {
        log.info("test method...a={}", stack.pop());
        System.out.println("收到请求:" + LocalDateTime.now().toLocalTime());
    }

    public static void main(String[] args) {
        System.out.println("收到请求:" + LocalDateTime.now().toLocalTime());
    }

}
