package com.leone.microservice.lock.controller;

import com.leone.microservice.lock.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-18
 **/
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/sell/zk")
    public void zookeeperSell(HttpServletRequest request) throws Exception {
        ticketService.zookeeperSell(LocalDateTime.now().toString() + " - " + request.getRemoteAddr() + ":" + request.getServerPort());
    }

    @GetMapping("/sell/redis")
    public void zookeeperRedis(HttpServletRequest request) throws Exception {
        ticketService.sellRedis(LocalDateTime.now().toString() + " - " + request.getRemoteHost() + ":" + request.getServerPort());
    }

}
