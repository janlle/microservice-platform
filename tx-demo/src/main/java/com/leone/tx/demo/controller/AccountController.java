package com.leone.tx.demo.controller;

import com.leone.tx.demo.entity.Account;
import com.leone.tx.demo.service.AccountService;
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

    @GetMapping("{from}/{to}")
    public String transfer(@PathVariable("from") String from, @PathVariable("to") String to, @RequestParam Integer total) {
        return accountService.transfer(from, to, total);
    }

    @GetMapping("{accountId}")
    public Account transfer(@PathVariable("accountId") Long accountId) {
        return accountService.findOne(accountId);
    }

}
