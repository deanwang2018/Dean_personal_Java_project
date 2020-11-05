package com.util;

/**
 * @author wangdian
 * @package com.util
 * @date 2020/11/4
 * @time 20:38
 */
public class Calculator {
    public static int result = 0;

    public static int add(int x, int y) {
        result = x + y;
        return result;
    }

    public static int count(int... x) throws InterruptedException {
//        int i = result;
//        Thread.sleep(1000);
//        result = i + x;
//        return result;
        int y = 0;
        for (int i = 0; i < x.length; i++) {
            y = y + x[i];
        }
        return y;
    }

    public static int subtract(int x, int y) {
        result = x - y;
        return result;
    }

    public static int multiply(int x, int y) {
        result = x * y;
        return result;
    }

    public static int divide(int x, int y) {
        result = x / y;
        return result;
    }
}
