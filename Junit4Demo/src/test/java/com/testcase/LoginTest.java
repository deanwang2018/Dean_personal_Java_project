package com.testcase;

import org.junit.Test;

/**
 * @author wangdian
 * @package com.testcase
 * @date 2020/9/28
 * @time 12:08
 */
public class LoginTest extends BaseTest{
    @Test
    public void logon(){
        dataMap.put("login","登录成功");
        System.out.println(dataMap.get("login"));
    }
}
