package testcase;

import org.junit.jupiter.api.Test;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 11:58
 */
public class BrowserTest extends BaseTest{
    @Test
    void browserTest(){
        try {
            driver.get("https://home.testing-studio.com");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
