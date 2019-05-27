package com.leone.microservice.transaction.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${app.lockName}")
    private String lockName;

    /**
     * redis 连接配置
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }

    /**
     * 获得锁对象实例
     *
     * @return
     */
    @Bean
    public RLock rLock() {
        return redissonClient().getLock(lockName);
    }


}
