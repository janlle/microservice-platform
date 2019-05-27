package com.leone.microservice.transaction.controller;

import com.leone.microservice.transaction.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-14
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String order(Integer productId, Integer count) {
        orderService.createOrder(productId, count);
        return "success";
    }

}
