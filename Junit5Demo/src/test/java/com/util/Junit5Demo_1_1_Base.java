package com.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package com.util
 * @date 2020/11/4
 * @time 20:41
 */
class Junit5Demo_1_1_Base {

    @Test
    void addTest() {
        int result = Calculator.add(4, 2);
        System.out.println(result);
        assertEquals(6, result);
        int result1 = Calculator.add(4, 2);
        int result2 = Calculator.add(5, 2);
        int result3 = Calculator.add(6, 2);
        assertAll("计算结果校验：",
                () -> assertEquals(6, result1),
                () -> assertEquals(7, result2),
                () -> assertEquals(8, result3)
        );
    }

    @Test
    void subTractTest() {
        int result = Calculator.subtract(4, 2);
        System.out.println(result);
        assertEquals(2, result);
    }

    @Test
    void multiplyTest() {
        int result = Calculator.multiply(4, 2);
        System.out.println(result);
        assertEquals(8, result);
    }

    @Test
    void divideTest() {
        int result = Calculator.divide(4, 2);
        System.out.println(result);
        assertEquals(2, result);
    }

    @Test
    void countTest() throws InterruptedException {
        int result = result = Calculator.count(1, 1, 1, 1);
        System.out.println(result);
        assertEquals(4, result);
    }
}