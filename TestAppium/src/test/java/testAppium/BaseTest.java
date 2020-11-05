package testAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/10/19
 * @time 14:36
 */
public class BaseTest {

    public static AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeAll
    static void setupEnv() throws MalformedURLException {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("platformName","Android");
            caps.setCapability("udid","127.0.0.1:7555");
            caps.setCapability("deviceName","127.0.0.1:7555");
            caps.setCapability("appPackage","com.xueqiu.android");
            caps.setCapability("appActivity",".view.WelcomeActivityAlias");
            caps.setCapability("noReset","true");

            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
