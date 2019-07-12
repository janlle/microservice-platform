package com.leone.microservice.limiting.arithmetic.counter;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> 计数器限流算法（比较暴力/超出直接拒绝）一定时间内只允许一定数量的请求访问
 *
 * @author leone
 * @since 2018-09-09
 **/
public class AtomicLimiterTest {

    // Atomic，限制总数
    private static final AtomicInteger atomic = new AtomicInteger(0);

    // 每秒最多支持 3 个请求通过
    private static int totalCount = 5;

    private static void atomicLimiter() {
        if (atomic.get() >= totalCount) {
            System.err.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + "\t\trefuse...");
        } else {
            try {
                atomic.incrementAndGet();
                // 处理核心逻辑
                System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + "\t\tallow...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                atomic.decrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(AtomicLimiterTest::atomicLimiter);
            Thread.sleep(100);
        }
        executorService.shutdown();
    }

}