package app.wework.src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
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
 * @date 2020/11/19
 * @time 11:30
 */
public class BasePage {
    public static AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    public AndroidDriver<MobileElement> ADriver;
    public TouchAction touchAction;

    //    todo: 参数化
    public String searchIcon = "hxw";
    public String searchField = "ghu";
    public String contactIcon = "通讯录";

    public BasePage() throws MalformedURLException {
        setupApp();
    }

    public ContactPage contact() throws MalformedURLException {
        return new ContactPage();
    }

    void setupApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("udid", "127.0.0.1:7555");
        caps.setCapability("deviceName", "127.0.0.1:7555");
        caps.setCapability("appPackage", "com.tencent.wework");
        caps.setCapability("appActivity", ".launch.WwMainActivity");
        caps.setCapability("noReset", "true");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        ADriver = (AndroidDriver<MobileElement>) this.driver;
        wait = new WebDriverWait(driver, 30);
        touchAction = new TouchAction(driver);
    }

    void search(String keyWords) {
//        点击搜索
        click(byId(searchIcon));
//        输入搜索内容
        sendKeys(byId(searchField), keyWords);
    }

    void click(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    public By byId(String id) {
        return MobileBy.id(id);
    }

    public By byText(String text) {
        return byXpath("//*[@text='" + text + "']");
    }

    public By byXpath(String xpath) {
        return By.xpath(xpath);
    }

    void PressAtPosition(int x, int y) {
        touchAction.press(PointOption.point(x, y)).release().perform();
    }

    public void quit() {
        driver.quit();
    }
}
