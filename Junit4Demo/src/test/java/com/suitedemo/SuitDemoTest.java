package com.suitedemo;

import com.testcase.BuyTest;
import com.testcase.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wangdian
 * @package com.suitedemo
 * @date 2020/9/28
 * @time 12:07
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        BuyTest.class
})
public class SuitDemoTest {
}
