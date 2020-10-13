package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.applet.Main;

import java.time.Duration;

/**
 * @author wangdian
 * @package page
 * @date 2020/10/13
 * @time 9:15
 */
public class ConTactPage {
    public static WebDriver driver;
    public static WebDriverWait wait;
    By addMember = By.linkText("添加成员");

    public ConTactPage addMember(String username, String acctid, String mobile) {
        // todo:
        GetDriver();

//        wait.until(ExpectedConditions.elementToBeClickable(addMember));
        while (driver.findElements(By.name("username")).size() == 0) {
            driver.findElement(addMember).click();
        }

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("acctid")).sendKeys(acctid);
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.cssSelector(".js_btn_save")).click();
        return this;
    }

    public static void GetDriver() {
        driver = MainPage.driver;
        wait = new WebDriverWait(driver, 10);
    }
}
