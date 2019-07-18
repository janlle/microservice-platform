package com.leone.microservice.lock;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-18
 **/
public interface Lock {

    void lock() throws Exception;

    boolean tryLock();

    void unlock() throws Exception;

}
