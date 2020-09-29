package com.testcase;

import org.junit.jupiter.api.*;

/**
 * @author wangdian
 * @package com.testcase
 * @date 2020/9/28
 * @time 15:40
 */
@DisplayName("junit5演示类")
public class Junit5Demo1Test {
    @BeforeAll
    static void setup(){
        System.out.println("beforeAll");
    }

    @BeforeEach
    void setupMethod(){
        System.out.println("beforeEach");
    }

    @Test
    @DisplayName("fun1测试方法")
    @RepeatedTest(5)
    void fun1(){
        System.out.println("fun1");
    }

    @Test
    @Disabled
    @DisplayName("fun2测试方法")
    void fun2(){
        System.out.println("fun2");
    }

    @AfterEach
    void cleanupMethod(){
        System.out.println("afterEach");
    }

    @AfterAll
    static void cleanup(){
        System.out.println("afterAll");
    }
}
