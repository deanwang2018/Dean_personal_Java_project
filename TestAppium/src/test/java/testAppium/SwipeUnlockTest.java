package testAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/10/19
 * @time 15:51
 */
public class SwipeUnlockTest {
    public static AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeAll
    static void setupEnv() throws MalformedURLException {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("platformName","Android");
            caps.setCapability("udid","127.0.0.1:7555");
            caps.setCapability("deviceName","127.0.0.1:7555");
            caps.setCapability("appPackage","cn.kmob.screenfingermovelock");
            caps.setCapability("appActivity","com.samsung.ui.FlashActivity");
            caps.setCapability("noReset","true");

            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    void unlockTest() throws Exception{
        TouchAction touchAction = new TouchAction(driver);

        Duration duration = Duration.ofMillis(3000);

        driver.findElement(By.id("cn.kmob.screenfingermovelock:id/patternTxt")).click();

        Thread.sleep(3000);

        touchAction.press(PointOption.point(131,197)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(403,197)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(672,197)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(672,462)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(672,735)).waitAction(WaitOptions.waitOptions(duration)).release().perform();
    }
}
