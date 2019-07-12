package com.leone.microservice.limiting.arithmetic.tokenbucket;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>令牌桶算法
 *
 * @author leone
 * @since 2019-04-19
 **/
public class RateLimiterTest {

    // 每秒生成 5 个令牌
    private static RateLimiter limiter = RateLimiter.create(5);

    public static void exec() {
        // 默认就是 1
        final double acquire = limiter.acquire(1);
        System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - blocking - " + acquire + " allow...");
        try {
            // 处理核心逻辑
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(RateLimiterTest::exec);
        }
        executorService.shutdown();
    }

}
