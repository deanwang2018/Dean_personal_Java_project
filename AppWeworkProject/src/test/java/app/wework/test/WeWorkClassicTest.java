package app.wework.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package app.wework.src
 * @date 2020/11/18
 * @time 20:21
 */
public class WeWorkClassicTest {
    public static AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeAll
    static void setupEnv() throws MalformedURLException {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("platformName","Android");
            caps.setCapability("udid","127.0.0.1:7555");
            caps.setCapability("deviceName","127.0.0.1:7555");
            caps.setCapability("appPackage","com.tencent.wework");
            caps.setCapability("appActivity",".launch.WwMainActivity");
            caps.setCapability("noReset","true");

            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    void search(){
//        点击搜索
        driver.findElement(MobileBy.id("hxw")).click();
//        输入搜索内容
        driver.findElement(MobileBy.id("ghu")).sendKeys("ceshi");
    }

    @Test
    void addMember(){
        AndroidDriver<MobileElement> driver2 = (AndroidDriver<MobileElement>) this.driver;
        wait = new WebDriverWait(driver, 10);
//        点击通讯录
        driver.findElement(By.xpath("//*[@text='通讯录']")).click();

//        点击添加成员
        driver.findElement(By.xpath("//*[@text='添加成员']")).click();

//        点击手动输入添加
        driver.findElement(By.xpath("//*[@text='手动输入添加']")).click();

//        输入成员信息
        By nameCode = By.xpath("//*[@resource-id='com.tencent.wework:id/ehw']/descendant::android.widget.EditText");
        By emailCode = By.xpath("//*[@resource-id='com.tencent.wework:id/eh_']/descendant::android.widget.EditText");
        By addressCode = By.xpath("//*[@resource-id='com.tencent.wework:id/egn']/descendant::android.widget.TextView");

        driver.findElement(nameCode).sendKeys("Ethan");
        driver.findElement(By.id("fiv")).sendKeys("18611111111");
        driver.findElement(emailCode).sendKeys("123456789@qq.com");
        driver.findElement(addressCode).click();
        driver.findElement(By.id("it")).sendKeys("腾讯大厦");
        driver.findElement(By.xpath("//*[@text='确定']")).click();
        driver.findElement(By.xpath("//*[@text='保存']")).click();
    }
}
