import com.sun.xml.internal.ws.resources.UtilMessages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/10/14
 * @time 16:01
 */
public class HelloWorldTest {
    private static AppiumDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setupEnv() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName","Android");
        caps.setCapability("udid","127.0.0.1:7555");
        caps.setCapability("deviceName","127.0.0.1:7555");
        caps.setCapability("appPackage","com.xueqiu.android");
        caps.setCapability("appActivity",".view.WelcomeActivityAlias");
        caps.setCapability("noReset","true");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void helloSnowBallTest() {
//        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_search");
//        el1.click();
//        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
//        el2.sendKeys("Alibaba");
//        MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[2]");
//        el3.click();

        // locate first page search framework
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();

        // locate search page search framework
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("阿里巴巴");

        driver.findElement(By.xpath("//*[@text='BABA']")).click();
        System.out.println(driver.findElement(By.id("current_price")).getText());
    }
}
