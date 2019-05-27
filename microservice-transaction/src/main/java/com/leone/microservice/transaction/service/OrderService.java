package com.leone.microservice.transaction.service;

import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-14
 **/
@Service
public class OrderService {

    /**
     * 创建订单(模拟分布式事务)
     *
     * @param productId
     * @param count
     */
    public void createOrder(Integer productId, Integer count) {
        // 商品服务减库存

        // 仓储服务创建物流单

        // 用户服务



    }


}
