package com.study.test.Lock;

public class LockMainTest {
    public static void main(String[] args) {
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

    }
}
