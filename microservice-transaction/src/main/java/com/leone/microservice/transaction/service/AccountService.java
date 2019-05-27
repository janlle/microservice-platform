package com.leone.microservice.transaction.service;

import com.leone.microservice.transaction.mapper.AccountMapper;
import com.leone.microservice.transaction.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-15
 **/
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 模拟转账
     *
     * @param from
     * @param to
     * @param total
     * @return
     */
    @Transactional
    public String transfer(String from, String to, Integer total) {
        int i1 = accountMapper.updateMoney(-total, from);

        // int i = 1 / 0;

        int i2 = accountMapper.updateMoney(total, to);
        return i1 == i2 ? "success" : "error";
    }

    /**
     * @param accountId
     * @return
     */
    public Account findOne(Long accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

}
