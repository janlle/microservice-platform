package com.leone.microservice.transaction.thread;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
public class AtomTest {

    private static int i = 1;

    public static void increment() {
        i = i++;
    }


    public static void main(String[] args) {
        System.out.println(AtomTest.i);
        for (int i = 0; i < 100; i++) {
            new Thread(AtomTest::increment).start();
        }
        System.out.println(AtomTest.i);
    }

}
