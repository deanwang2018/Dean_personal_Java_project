package web.wework.src;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/14
 * @time 15:49
 */
public class WebWeworkHelperFactory {
    public static WebDriver driver;
    public String url;
    public static WebDriverWait wait;
    public ChromeOptions options;
    public static Actions actions;

    void addCookiesAndLogin() throws IOException {
        driver.get(url);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);

        cookies.forEach(cookieMap -> driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString())));

        driver.navigate().refresh();
    }

    void getCookies() throws IOException, InterruptedException {
        driver.get(url);

        // 等待时扫码登录，或者debug停住，扫码登录
        Thread.sleep(20000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);

        Assert.assertEquals("cookies.yaml为空！", true, !cookies.isEmpty());

        System.exit(0);
    }

    public WebWeworkHelperFactory(WebDriver driver) {
        this.driver = driver;
    }

    public WebWeworkHelperFactory() {

    }

    static void waitAndClick(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        click(by);
    }

    static void click(By by) {
        driver.findElement(by).click();
    }

    static void waitAndClick(By by, int number) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        System.out.println(number);
        click(by, number);
    }

    static void click(By by, int number) {
        System.out.println(number);
        driver.findElements(by).get(number).click();
    }

    static void sendKeys(By by, String keys) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(keys);
    }

    static void waitFadOut(By by, int timeout) throws InterruptedException {
        while (driver.findElement(by).isDisplayed() && timeout != 0) {
            System.out.println("sleep left : " + timeout);
            Thread.sleep(1000);
            timeout = timeout - 1;
        }
    }

    public static void quitDriver() {
        driver.quit();
    }

    public UIAutoContact load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAutoContact uiAutoContact = mapper.readValue(MainPage.class.getResourceAsStream(path), UIAutoContact.class);
        return uiAutoContact;
    }
}
