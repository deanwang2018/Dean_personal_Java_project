package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 11:16
 */
public class FrameTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void initData() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver,3);
    }

    @Test
    void frameTest(){
        driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

        driver.switchTo().frame("iframeResult");

        System.out.println(driver.findElement(By.id("draggable")).getText());

        driver.switchTo().parentFrame();

        System.out.println(driver.findElement(By.id("submitBTN")).getText());
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
