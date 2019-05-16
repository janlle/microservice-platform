package com.leone.tx.demo.service;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-15
 **/
@Service
public class ZkLockService {

    private static final Logger log = LoggerFactory.getLogger(ZkLockService.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private InterProcessMutex lock;

    /**
     * @param from
     * @param to
     * @param total
     * @return
     */
    public String transfer(String from, String to, Integer total) {
        String result = null;
        try {
            log.info("尝试获取锁...");
            lock.acquire();

            log.info("[获取锁]，开始执行业务...");
            result = accountService.transfer(from, to, total);
            log.info("[获取锁]，业务执行完毕...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
                log.info("释放锁...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
