package com.study.test.util;

import java.util.*;

public class SetTest {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "小丁");
        map.put("age", 8);
        map.put("address", "天上飞来的");

        List<HashMap<String, Object>>  list  = new ArrayList<>();
        List<HashMap<String, Object>>  list1  = new ArrayList<>();

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "小丁");
        map1.put("age", 8);
        map1.put("address", "地上跑的");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "小丁");
        map2.put("age", 8);
        map2.put("address", "天上飞来的");


        // map 和 map2 的值一样,但内存引用不一样, 只要按照相同的顺序添加到不同的list中, set<list> 时,照样会去重, 顺序不一样不会去重
        list.add(map);
        list.add(map1);

//        list1.add(map);
        list1.add(map2);
        list1.add(map1);
        Set set = new HashSet();
//        set.add(list);
//        set.add(list1);

        System.out.println(set.toString());

        // 升级版, 上面的方法如果顺序不对就无法去重, 下面使用list的addAll 方法, 此方法可以将两个list 合并
        list1.addAll(list);
        set.addAll(list1);
        System.out.println("方法set" + set.toString());
        list.clear();
        list.addAll(set);
        System.out.println("方法list" + set.toString());

        HashSet<HashMap<String, Object>> h = new HashSet<HashMap<String, Object>>(list1);
        list1.clear();
        list1.addAll(h);
        System.out.println("方法2" + h.toString());
        System.out.println("方法list1" + list1.toString());

        // 总结1 : add 字符串时, 一样就不添加哦
        // 2: map 对象. 如果map 里面有一个不一样就会添加哦, 除非全部一样就会去重
        // 3: list 对象, 如果 list 存放map, set 去重 跟list添加map的顺序有关系. 跟 map的 对象无关
    }
}
