package app.wework.pageobject;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.framework.AndroidLocator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package app.wework.src
 * @date 2020/11/19
 * @time 11:30
 */
public class BasePage {
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
    public DesiredCapabilities caps;
    public TouchAction touchAction;
    public String packageName;
    public String viewName;
    private String noReset = "true";
    private String dontStopAppOnReset = "false";
    private final int defaultTimeOut = 30;
    private By by;

    //    todo: 参数化
    public String searchIcon = "hxw";
    public String searchField = "ghu";

    public BasePage() {
    }

    public BasePage(String packageName, String viewName) throws MalformedURLException {
        this.packageName = packageName;
        this.viewName = viewName;
        setupApp(this.packageName, this.viewName);
    }

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, defaultTimeOut);
    }

    void setupApp(String packageName, String viewName) throws MalformedURLException {
        caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("udid", "127.0.0.1:7555");
        caps.setCapability("deviceName", "127.0.0.1:7555");
        caps.setCapability("appPackage", packageName);
        caps.setCapability("appActivity", viewName);
        caps.setCapability("noReset", noReset);
        caps.setCapability("dontStopAppOnReset", dontStopAppOnReset);

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, defaultTimeOut);

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

    public AndroidLocator load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AndroidLocator elementLocator = mapper.readValue(BasePage.class.getResourceAsStream(path), AndroidLocator.class);
        return elementLocator;
    }

    public List<HashMap<String, JSONObject>> getElement() throws IOException {
        AndroidLocator elementLocator = load("/android.yaml");
        return elementLocator.contact;
    }

    void PressAtPosition(int x, int y) {
        touchAction.press(PointOption.point(x, y)).release().perform();
    }

    //只滚动到第一个匹配的text
    void ScrollToTextAndClick(String text) {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) this.driver;
        String UiScrollable = "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                "scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0));\n";
        driver.findElementByAndroidUIAutomator(UiScrollable).click();
    }

    public void quit() {
        driver.quit();
    }
}
