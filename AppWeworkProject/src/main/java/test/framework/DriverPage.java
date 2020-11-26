package test.framework;

import app.wework.pageobject.BasePage;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package test.framework
 * @date 2020/11/25
 * @time 22:23
 */
public class DriverPage {
    List<HashMap<String, Object>> app;
    List<HashMap<String, Object>> web;

    void setupApp(String packageName, String viewName, AppiumDriver<MobileElement> driver, WebDriverWait wait) throws MalformedURLException {
        DesiredCapabilities caps;
        caps = new DesiredCapabilities();

        HashMap<String, Object> appConfig = (HashMap<String, Object>) app;


        caps.setCapability("platformName", appConfig.get("platformName"));
        caps.setCapability("udid", appConfig.get("udid"));
        caps.setCapability("deviceName", appConfig.get("deviceName"));
        caps.setCapability("appPackage", packageName);
        caps.setCapability("appActivity", viewName);
        caps.setCapability("noReset", appConfig.get("noReset"));
        caps.setCapability("dontStopAppOnReset", appConfig.get("dontStopAppOnReset"));

        driver = new AndroidDriver<>(new URL(appConfig.get("remoteUrl").toString()), caps);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(appConfig.get("implicitlyWait").toString()), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Integer.parseInt(appConfig.get("WebDriverWait").toString()));
    }

    public Object load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object elementLocator = mapper.readValue(BasePage.class.getResourceAsStream(path), AndroidLocator.class);
        return elementLocator;
    }


}
