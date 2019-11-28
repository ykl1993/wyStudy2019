package com.study.test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TEST986806369 {
    public static void main(String[] args) {
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("a","aa");
        map1.put("b","bbbbb");
        map1.put("b","bb");
        map1.put("c","cc");
        map1.put("d","dd");
        map1.put("e","ee");
        map1.put("f","ff");
        map1.put("g","gg");
        map1.put("h","hh");
        map1.put("i","ii");
        map1.put("j","jj");
        map1.put("1","jj");
        map1.put("2","jj");
        map1.put("3","jj");
        map1.put("4","jj");
        map1.put("5","jj");
        map1.put("6","jj");
        map1.put("7","jj");
        map1.put("8","jj");
        map1.put("9","jj");
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a","ykl");
        int a = 12 << 1;
        System.out.println(a);
    }

}
