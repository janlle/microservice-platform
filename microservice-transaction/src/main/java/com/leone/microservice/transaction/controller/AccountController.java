package com.leone.microservice.transaction.controller;

import com.leone.microservice.transaction.entity.Account;
import com.leone.microservice.transaction.service.AccountService;
import com.leone.microservice.transaction.service.RedisLockService;
import com.leone.microservice.transaction.service.ZkLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-15
 **/
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ZkLockService zkLockService;

    @Autowired
    private RedisLockService redisLockService;

    @GetMapping("{from}/{to}")
    public String transfer(@PathVariable("from") String from, @PathVariable("to") String to, @RequestParam Integer total) {
        return redisLockService.transfer(from, to, total);
    }

    @GetMapping("{accountId}")
    public Account transfer(@PathVariable("accountId") Long accountId) {
        return accountService.findOne(accountId);
    }

}
