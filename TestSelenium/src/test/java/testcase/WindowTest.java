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
 * @time 8:52
 */
public class WindowTest {
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
    void switchWindowTest() {
        try {
            driver.get("https://www.baidu.com");
            driver.manage().window().maximize();

            // 百度首页的句柄窗口
            String baiduWin = driver.getWindowHandle();

            driver.findElement(By.xpath("//a[@class='s-top-login-btn c-btn c-btn-primary c-btn-mini lb']")).click();

            driver.findElement(By.xpath("//a[@class='pass-reglink pass-link']")).click();

            for (String win : driver.getWindowHandles()) {
                if (!win.equals(baiduWin)) {
                    driver.switchTo().window(win);

                    driver.findElement(By.xpath("//input[@id='TANGRAM__PSP_4__userName']")).clear();
                    driver.findElement(By.xpath("//input[@id='TANGRAM__PSP_4__userName']")).sendKeys("dean");
                    driver.findElement(By.xpath("//input[@id='TANGRAM__PSP_4__phone']")).clear();
                    driver.findElement(By.xpath("//input[@id='TANGRAM__PSP_4__phone']")).sendKeys("18600000000");
                    // 密码，验证码输入后，点击注册
                }
            }

            Thread.sleep(3000);

            driver.switchTo().window(baiduWin);

            driver.findElement(By.xpath("//p[@class='tang-pass-footerBarULogin pass-link']")).click();

            driver.findElement(By.xpath("//input[@class='pass-text-input pass-text-input-userName']")).clear();
            driver.findElement(By.xpath("//input[@class='pass-text-input pass-text-input-userName']")).sendKeys("18600000000");

            driver.findElement(By.xpath("//input[@class='pass-text-input pass-text-input-password']")).clear();
            driver.findElement(By.xpath("//input[@class='pass-text-input pass-text-input-password']")).sendKeys("18600000000");

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
