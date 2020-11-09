package com.util;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package com.util
 * @date 2020/11/4
 * @time 20:41
 */
class Junit5Demo_1_1_Base {
//    @BeforeEach
//    public void clean(){
//        Calculator.clear();
//    }

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

    @RepeatedTest(10)
    void countTest() throws InterruptedException {
//        int result = Calculator.count(1, 1, 1, 1);
        int result = Calculator.count(1);
        System.out.println("thread-" + Thread.currentThread().getId() + ", " + result);
//        assertEquals(1, result);
    }

    @Epic("Epic 计算器项目")
    @Description("Description")
    @DisplayName("DisplayName 累加并发测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://ceshiren.com/t/topic/7718")
    @RepeatedTest(4)
    void countAtomTest() throws InterruptedException {
        int result = Calculator.countAtom(1);
        System.out.println(result);
//        assertEquals(1, result);
    }
}