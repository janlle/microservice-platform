package com.leone.microservice.test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class AbstractChild extends AbstractTest {

    @Override
    public int world(Integer integer) {
        System.out.println("AbstractChild " + integer);
        return integer + 1;
    }
}
