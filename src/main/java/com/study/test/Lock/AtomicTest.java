package com.study.test.Lock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i=0; i < 2; i ++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j ++) {
                    int num = atomicInteger.incrementAndGet();
                    System.out.println(num);
                }
            }).start();
        }
    }
}
