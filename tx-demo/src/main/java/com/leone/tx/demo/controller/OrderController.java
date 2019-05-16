package com.leone.tx.demo.controller;

import com.leone.tx.demo.service.OrderService;
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
