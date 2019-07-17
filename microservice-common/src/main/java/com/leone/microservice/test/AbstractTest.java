package com.leone.microservice.test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public abstract class AbstractTest {

    public String hello(String message) {
        System.out.println("hello " + message);
        return message;
    }

    public abstract int world(Integer integer);

}
