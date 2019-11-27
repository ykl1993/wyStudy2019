package com.study.test;

import java.util.HashMap;

public class Test1107 {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Test1107.class.getClassLoader().loadClass("java.lang.String").getClassLoader());
        System.out.println(Test1107.class.getClass().getName());
        System.out.println(Test1107.class.getClass().getSimpleName());
        System.out.println(Test1107.class.getClassLoader());
        HashMap<String, Object> map = new HashMap<>();
        map.put("a","ykl");
        map.put("b","ykl2");
       int i = (16 - 1) & 3;
        System.out.println(i);
    }
}
