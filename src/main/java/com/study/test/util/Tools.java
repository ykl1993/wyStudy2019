package com.study.test.util;

public class Tools {
    public static int toInt(Object object) {
        if (object == null) {
            return 0;
        }
        int b;
        try {
            b = (int) Double.parseDouble(object.toString());
        } catch (Exception e) {
            return 0;
        }
        return b;
    }

    public static double toDouble(Object object) {
        if (object == null) {
            return 0;
        }
        double b;
        try {
            b = Double.parseDouble(object.toString());
        } catch (Exception e) {
            return 0;
        }
        return b;
    }

    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(Tools.toInt("14.8"));
        System.out.println(Tools.toDouble("16.88"));
    }
}
