import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/9/28
 * @time 15:55
 */
public class LoginTest {
    private static HashMap<String, Object> dataMap = new HashMap<String, Object>();

    @Test
    void loginTest() {
        dataMap.put("login", "登录成功");
    }

    @Nested
    class payTest {
        @Test
        void payTest() {
            if (null != dataMap.get("buy")) {
                System.out.println("正在支付请等待");
                System.out.println(dataMap.get("buy"));
            } else {
                System.out.println("你还没有购买课程呢，赶紧去买");
            }
        }
    }

    @Nested
    class ButTest {
        @Test
        void buTest() {
            if (dataMap.get("login").equals("登录成功")) {
                System.out.println("登录成功啦，可以购买东西了");
                dataMap.put("buy", "购买了霍格沃兹测试学院名企测试课程");
            } else {
                System.out.println("登录失败，重新登录吧");
            }
        }
    }
}
