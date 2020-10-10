package testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 14:34
 */
public class JsTest extends BaseTest{

    @Test
    void jsTest(){
        try {
            driver.get("https://www.12306.cn/index/");

            JavascriptExecutor jsDriver = (JavascriptExecutor)driver;

            Thread.sleep(3000);

            jsDriver.executeScript("document.getElementById('train_date').value='2020-12-30'");

            System.out.println(jsDriver.executeScript("return document.getElementById('train_date').value"));

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
