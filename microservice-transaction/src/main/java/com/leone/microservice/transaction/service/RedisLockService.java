package com.leone.microservice.transaction.service;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
@Service
public class RedisLockService {

    private static final Logger log = LoggerFactory.getLogger(RedisLockService.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RLock rLock;

    /**
     * @param from
     * @param to
     * @param total
     * @return
     */
    public String transfer(String from, String to, Integer total) {
        String result = null;
        boolean res = false;
        try {
            log.info("尝试获取锁...");
            // 1.不支持过期自动解锁，不会超时
            // rLock.lock();

            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            // lock.lock(10, TimeUnit.SECONDS);

            // 3. 尝试加锁，最多等待20秒，上锁以后10秒自动解锁（实际项目中推荐这种，以防出现死锁）
            res = rLock.tryLock(20, 10, TimeUnit.SECONDS);

            log.info(" 获取锁...");
            if (res) {
                result = accountService.transfer(from, to, total);
                log.info("exec success...");
            } else {
                log.warn(" 等待超时...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (res) {
                log.info(" 释放锁...");
                rLock.unlock();
            }
        }
        return result;
    }

}
