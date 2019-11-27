package com.study.test.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1 {
    volatile int i = 0;
    Lock lock = new ReentrantLock();
    public void add() {
        lock.lock();
        i++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Lock1 lock1 = new Lock1();
        for (int i = 0; i < 10000; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock1.add();
                    System.out.println(lock1.i);
                }
            }).start();
        }
//        Thread.sleep(1000 * 2);

    }
}
