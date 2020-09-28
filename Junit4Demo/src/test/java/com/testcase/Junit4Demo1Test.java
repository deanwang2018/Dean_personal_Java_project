package com.testcase;

import org.junit.*;

/**
 * @author wangdian
 * @package com.testcase
 * @date 2020/9/28
 * @time 10:50
 */
public class Junit4Demo1Test {
    @BeforeClass
    public static void setup(){
        System.out.println("before class");
    }

    @Before
    public void beforeTest(){
        System.out.println("before");
    }

    @Test
    public void fun1(){
        System.out.println("fun1 test1");
    }

    @Test
    public void fun2(){
        System.out.println("fun2 test2");
    }

    @Test
    @Ignore
    public void fun3(){
        System.out.println("fun3 test3");
    }

    @After
    public void afterTest(){
        System.out.println("after");
    }

    @AfterClass
    public static void cleanup(){
        System.out.println("after class");
    }
}