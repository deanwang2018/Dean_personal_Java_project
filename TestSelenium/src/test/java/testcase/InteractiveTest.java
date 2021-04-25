package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/9
 * @time 19:55
 */
public class InteractiveTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    @BeforeAll
    public static void initData() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver,3);
    }

    @Test
    void clickTest() {
        try {
            driver.get("http://sahitest.com/demo/clicks.htm");

//        /html/body/form/input[3]
            actions.doubleClick(driver.findElement(By.xpath("//input[@value='dbl click me']")));
            actions.click(driver.findElement(By.xpath("//input[@value='click me']")));
            actions.contextClick(driver.findElement(By.xpath("//input[@value='right click me']")));

            actions.perform();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void moveMouseTest() {
        try {
            driver.get("https://www.baidu.com");

            actions.moveToElement(driver.findElement(By.id("s-usersetting-top")));

            actions.perform();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void dragTest() {
        try {
            driver.get("http://sahitest.com/demo/dragDropMooTools.htm");

            actions.dragAndDrop(driver.findElement(By.id("dragger")), driver.findElement(By.xpath("/html/body/div[2]")));

            actions.perform();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void keyboardTest() {
        try {
            driver.get("http://sahitest.com/demo/label.htm");
            driver.findElements(By.xpath("//input[@type='textbox']")).get(0).sendKeys("dean");
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
            actions.keyDown(driver.findElements(By.xpath("//input[@type='textbox']")).get(1), Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void scrollTest() {
        try {
            driver.get("https://www.baidu.com");
            driver.findElement(By.id("kw")).sendKeys("霍格沃兹测试学院");

            TouchActions actions = new TouchActions(driver);

            actions.click( driver.findElement(By.id("su")));

            JavascriptExecutor js = (JavascriptExecutor)driver;

            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

            Thread.sleep(3000);

            driver.findElement(By.xpath("//a[@class='n']")).click();

            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}