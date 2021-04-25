package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/9
 * @time 11:11
 */
public class AiceTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void initData(){
       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       wait = new WebDriverWait(driver,3);
    }

    @Test
    void login(){
        driver.get("https://home.testing-studio.com");

        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
        element.click();

        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys("dean");

        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("dean");

        driver.findElement(By.id("login-button")).click();
    }

    @Test
    void waitTest(){
        driver.get("https://home.testing-studio.com");

        WebElement loginEle = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
            }
        });

        loginEle.click();

//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
//        element.click();
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
