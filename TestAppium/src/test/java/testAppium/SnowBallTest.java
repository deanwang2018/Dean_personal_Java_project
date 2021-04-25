package testAppium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/10/19
 * @time 14:36
 */
public class SnowBallTest extends BaseTest{

    @Test
    void searchTest(){
        WebElement home_search = driver.findElement(By.id("com.xueqiu.android:id/home_search"));

        if(home_search.getAttribute("enabled").equals("true")){
            System.out.println(home_search.getLocation().toString());
            home_search.click();

            WebElement search_input_text = driver.findElement(By.id("com.xueqiu.android:id/search_input_text"));

            if(search_input_text.getAttribute("displayed").equals("true")){
                search_input_text.sendKeys("alibaba");
                System.out.println("搜索成功");

            } else{
                System.out.println("搜索失败");
            }
        }
    }

    @Test
    void swipeTest(){
        try {
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();

            Thread.sleep(10000);

            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point((int)(width*0.5),(int)(height*0.8))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                    .moveTo(PointOption.point((int)(width*0.5),(int)(height*0.2))).release().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
