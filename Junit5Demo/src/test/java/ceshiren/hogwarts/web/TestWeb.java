package ceshiren.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/11
 * @time 20:26
 */
public class TestWeb {
    public static WebDriver driver;
    public static String url;
    public static WebDriverWait wait;
    public static ChromeOptions options;

    public static By member = By.xpath("//span[contains(.,'添加成员')]");
    public static By userName = new By.ByCssSelector("#username");
    public static By aliasName = new By.ByCssSelector("#memberAdd_english_name");
    public static By acctNumber = new By.ByCssSelector("#memberAdd_acctid");
    public static By phoneNumber = new By.ByCssSelector("#memberAdd_phone");
    public static By save = new By.ByCssSelector(".member_colRight_operationBar:nth-child(3) > .js_btn_save");
    public static By search = new By.ByCssSelector("#memberSearchInput");

    @BeforeAll
    static void setupEnv() {
        options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        url = "https://work.weixin.qq.com/wework_admin/frame";
    }

    @Test
    void testSearch() {
        driver.get(url);
        driver.findElement(By.cssSelector(".d-icon-search")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
    }

    @Test
    void getCookies() throws IOException, InterruptedException {
        driver.get(url);

        // 等待时扫码登录，或者debug停住，扫码登录
        Thread.sleep(20000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);

        Assert.assertEquals("cookies.yaml为空！", true, !cookies.isEmpty());
    }

    @Test
    void addCookiesAndLoginToAddMember() throws IOException, InterruptedException {
        driver.get(url);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
//        };
        TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);

        cookies.forEach(cookieMap -> driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString())));

        driver.navigate().refresh();

        wait.until(ExpectedConditions.elementToBeClickable(member));
        driver.findElement(member).click();

        wait.until(ExpectedConditions.elementToBeClickable(save));
        driver.findElement(userName).sendKeys("dean");
        driver.findElement(aliasName).sendKeys("dean");
        driver.findElement(acctNumber).sendKeys("18611111111");
        driver.findElement(phoneNumber).sendKeys("18611111111");

        driver.findElement(save).click();

        wait.until(ExpectedConditions.elementToBeClickable(search));
        driver.findElement(search).click();
        driver.findElement(search).sendKeys("dean");

        String actural = driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();

        Assert.assertEquals("实际获取到的名字与期待的dean不符，实际为：" + actural, actural, "dean");
    }

//    @AfterAll
//    static void cleanup() {
//        driver.quit();
//    }
}
