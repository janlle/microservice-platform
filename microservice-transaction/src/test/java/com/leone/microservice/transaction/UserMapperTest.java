package com.leone.microservice.transaction;

import com.leone.microservice.transaction.entity.Account;
import com.leone.microservice.transaction.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TransactionApplication.class})
public class UserMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    //@Test
    public void transTest() {
        Account account1 = accountMapper.selectByPrimaryKey(1L);
        Account account2 = accountMapper.selectByPrimaryKey(1L);

        System.out.println(account1);
        System.out.println(account2);


        account1.setMoney(100);
        int i1 = accountMapper.updateMoneyVersion(account1);
        System.out.println("update1: " + (i1 == 1 ? "success" : "failed"));

        account1.setMoney(100);
        int i2 = accountMapper.updateMoneyVersion(account2);
        System.out.println("update2: " + (i2 == 1 ? "success" : "failed"));


    }

}
