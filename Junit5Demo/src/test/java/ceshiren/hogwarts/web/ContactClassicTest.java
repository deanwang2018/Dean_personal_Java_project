package ceshiren.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
public class ContactClassicTest {
    public static WebDriver driver;
    public static String url;
    public static WebDriverWait wait;
    public static ChromeOptions options;
    public static File file;

    //    public static By member = By.xpath("//span[contains(.,'添加成员')]");
//    public static By member = By.linkText("添加成员");
    public static By member = By.cssSelector("[node-type=addmember]");
    public static By userName = new By.ByCssSelector("#username");
    public static By aliasName = new By.ByCssSelector("#memberAdd_english_name");
    public static By acctNumber = new By.ByCssSelector("#memberAdd_acctid");
    public static By phoneNumber = new By.ByCssSelector("#memberAdd_phone");
    //    public static By save = new By.ByCssSelector(".member_colRight_operationBar:nth-child(3) > .js_btn_save");
    public static By save = By.linkText("保存");
    public static By search = new By.ByCssSelector("#memberSearchInput");
    public static By menuContact = new By.ByCssSelector("#menu_contacts");

    @BeforeAll
    static void setupEnv() throws IOException, InterruptedException {
        file = new File("cookies.yaml");
        options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        url = "https://work.weixin.qq.com/wework_admin/frame";

        if (!file.exists()) {
            getCookies();
        } else {
            addCookiesAndLogin();
        }
    }

    @Test
    void testSearch() {
        driver.get(url);
        driver.findElement(By.cssSelector(".d-icon-search")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
    }

    static void getCookies() throws IOException, InterruptedException {
        driver.get(url);

        // 等待时扫码登录，或者debug停住，扫码登录
        Thread.sleep(20000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);

        assertEquals(true, !cookies.isEmpty(), "cookies.yaml为空！");

        System.exit(0);
    }

    static void addCookiesAndLogin() throws IOException, InterruptedException {
        driver.get(url);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
        };
        List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"), typeReference);
        System.out.println(cookies);

        cookies.forEach(cookieMap -> driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString())));

        driver.navigate().refresh();
    }

    @Test
    void contactAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(member));
        click(member);

        wait.until(ExpectedConditions.elementToBeClickable(save));
        sendKeys(userName, "dean");
        sendKeys(aliasName, "dean");
        sendKeys(acctNumber, "18611111111");
        sendKeys(phoneNumber, "18611111111");

        click(save);

        wait.until(ExpectedConditions.elementToBeClickable(search));
        driver.findElement(search).click();
        driver.findElement(search).sendKeys("dean");

        String actural = driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();

        assertEquals("dean", actural, "实际获取到的名字与期待的dean不符，实际为：" + actural);
    }

    @Test
    void search() {

    }

    @Test
    void departmentSearch() {
        click(menuContact);
        sendKeys(search, "科技部");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(content.contains("无任何成员"));
    }

    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String keys) {
        driver.findElement(by).sendKeys(keys);
    }

    @AfterAll
    static void cleanup() {
        driver.quit();
        if(file.exists()){

        }
    }
}
