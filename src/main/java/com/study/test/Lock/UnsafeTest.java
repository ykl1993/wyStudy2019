package com.study.test.Lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    volatile int i = 0;
    private static Unsafe unsafe = null;
    private static Long valueOffset;
    static {
//        unsafe = Unsafe.getUnsafe();
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            System.out.println(field);
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Field iField = UnsafeTest.class.getDeclaredField("i");
            // 获取当前对象的内存偏移量
            valueOffset = unsafe.objectFieldOffset(iField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        while(true) {
            // 获取旧值,
            //参数1 : 内存中的哪个对象, 参数2 : 对象的偏移量
            int current = unsafe.getIntVolatile(this, valueOffset);
            //参数1 : 内存中的哪个对象, 参数2 : 对象的偏移量 ; 参数3: 旧值, 参数4: 新值
            // 如果执行cas 操作成功则退出, 否则自旋继续进行cas
            if ( unsafe.compareAndSwapInt(this,valueOffset,current, current + 1)) {
                return;
            }
        }

    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeTest.add();
                    System.out.println(unsafeTest.i);
                }
            }).start();
        }
    }
}
