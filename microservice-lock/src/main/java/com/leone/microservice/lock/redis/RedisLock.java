package com.leone.microservice.lock.redis;

import com.leone.microservice.lock.Lock;
import org.checkerframework.checker.units.qual.A;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-18
 **/
@Component
public class RedisLock implements Lock {

    @Autowired
    private RedisTemplate redisTemplate;

    // 使用 redis 的一个框架实现 redis 的分布式锁
    @Autowired
    private RedissonClient redissonClient;


    @Override
    public void lock() throws Exception {
        redissonClient.getLock("");
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public void unlock() throws Exception {

    }
}
