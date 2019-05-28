package com.leone.microservice.Test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Main {

    public static void main(String[] args) {
        AbstractTest a = new AbstractChild();
        a.hello("james");
        a.world(1);

    }

}
