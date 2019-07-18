package com.leone.microservice.lock.service;

import com.leone.microservice.lock.redis.RedisLock;
import com.leone.microservice.lock.zookeeper.ZkLock;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-18
 **/
@Slf4j
@Service
public class TicketService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ZkLock zkLock;

    @Autowired
    private RedisLock redisLock;

    /**
     * @param requestId 不同服务的标识，规则为[时间戳 - host - port]
     */
    public void zookeeperSell(String requestId) throws Exception {
        zkLock.lock();
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger("ticketCount", redisTemplate.getConnectionFactory());
        int ticketCount = redisAtomicInteger.getAndDecrement();
        log.info("requestId: " + requestId + " ticketCount: " + ticketCount);
        zkLock.unlock();
    }


    /**
     * @param requestId 不同服务的标识，规则为[时间戳 - host - port]
     */
    public void sellRedis(String requestId) throws Exception {

    }

}
