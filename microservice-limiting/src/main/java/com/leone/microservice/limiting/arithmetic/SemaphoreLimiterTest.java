package com.leone.microservice.limiting.arithmetic;

import com.leone.microservice.limiting.arithmetic.tokenbucket.RateLimiterTest;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>计数器限流算法（允许将任务放入到缓冲队列） 信号量，用来达到削峰的目的
 *
 * @author leone
 * @since 2018-09-09
 **/
public class SemaphoreLimiterTest {

    // 一定量信号量
    private static final Semaphore semaphore = new Semaphore(3);

    private static void semaphoreLimiter() {
        // 队列中允许存活的任务个数不能超过 5 个
        if (semaphore.getQueueLength() > 5) {
            System.err.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + "\t\trefuse...");
        } else {
            try {
                semaphore.acquire();
                System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + "\t\tallow...");
                // 处理核心逻辑
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(SemaphoreLimiterTest::semaphoreLimiter);
            Thread.sleep(100);
        }
        executorService.shutdown();
    }

}