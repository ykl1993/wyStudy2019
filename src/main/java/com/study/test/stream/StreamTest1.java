package com.study.test.stream;

import com.study.test.util.Tools;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest1 {
    public static void main(String[] args) {
        // list<map> 数据分组汇总, 字段之间的加减
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> stu1 = new HashMap<String, Object>();
        stu1.put("name", "卫庄");
        stu1.put("score", new BigDecimal(80));
        stu1.put("price", new BigDecimal(5.5));
        Object put = stu1.put("UID", "xcbin");
        list.add(stu1);
        Map<String,Object> stu2 = new HashMap<String, Object>();
        stu2.put("name", "盖聂");
        stu2.put("score", new BigDecimal(90));
        stu2.put("price", new BigDecimal(4.5));
        stu2.put("UID", "xcbin");
        list.add(stu2);
        Map<String,Object> stu3 = new HashMap<String, Object>();
        stu3.put("name", "天明");
        stu3.put("score", new BigDecimal(60));
        stu3.put("price", new BigDecimal(6.5));
        stu3.put("UID", "xcbin");
        list.add(stu3);
        Map<String,Object> stu4 = new HashMap<String, Object>();
        stu4.put("name", "卫庄");
        stu4.put("score", new BigDecimal(30));
        stu4.put("price", new BigDecimal(3.5));
        stu4.put("UID", "xcbin");
        list.add(stu4);
        Map<String,Object> stu5 = new HashMap<String, Object>();
        stu5.put("name", "天明");
        stu5.put("score", new BigDecimal(60));
        stu5.put("price", new BigDecimal(6.5));
        stu5.put("UID", "xcbin");
        list.add(stu3);

        // limit 取前n条数据
        List<Map<String,Object>> limit_list = list.stream().limit(1).collect(Collectors.toList());
        System.out.println("limit 结果: " + limit_list);

        // skip  跳过前n条数据, 取出之后的数据
        List<Map<String,Object>> skip_list = list.stream().skip(1).collect(Collectors.toList());
        System.out.println("skip 结果: " + skip_list);

        // skip + limit  跳过前n条数据, 剩余的数据又取前n条
        List<Map<String,Object>> skipAndLimit_list = list.stream().skip(1).limit(2).collect(Collectors.toList());
        System.out.println("skip + limit 结果: " + skipAndLimit_list);

        // filter 接收 Lambda ， 从流中排除某些元素。 (Predicate)
        List<Map<String,Object>> filter_list = list.stream().filter((a) -> {
//            return Tools.toDouble(a.get("price")) > 6;
//            return Tools.toString(a.get("name")).equals("卫庄");
            return a.containsValue("天明");
        }).collect(Collectors.toList());
        System.out.println("filter (Predicate类) 结果: " + filter_list);

        // distinct 筛选，通过流所生成元素的 hashCode() 和 equals() 去 除重复元素
        List<Map<String,Object>> distinct_list = list.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct 结果: " + distinct_list);

        // sorted 产生一个新流，其中按自然顺序排序（因为是map对象无法自然排序）
        // 产生一个新流，其中按比较器顺序排序
        List<Map<String,Object>> sorted_list = list.stream().sorted((e1,e2) -> {
//            return Tools.toInt(e1.get("score")) - Tools.toInt(e2.get("score"));
            // Comparator.comparing(Person::getName);  这个比较方法是下面方法的简写, 但是map 不能使用,需实体类且有get,set方法
            return Tools.toString(e1.get("name")).compareTo(Tools.toString(e2.get("name")));
        }).collect(Collectors.toList());
        System.out.println("sorted 结果: " + sorted_list);
    }
}
