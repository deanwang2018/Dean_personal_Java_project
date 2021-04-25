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
    By tips = By.linkText("添加成功");
    By delete = By.linkText("删除");
    By confirm = By.linkText("确认");
    By clearSearch = By.id("clearMemberSearchInput");
    By addDep = By.linkText("添加部门");


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

    public ConTactPage search(String keywords) {
        driver.findElement(By.id("memberSearchInput")).sendKeys(keywords);
        while (driver.findElements(tips).size() > 0) {
            ExplicitWaitToClick(delete);
        }
        return this;
    }

    public ConTactPage delete() {
        ExplicitWaitToClick(delete);
        driver.findElement(delete).click();
        ExplicitWaitToClick(confirm);
        driver.findElement(confirm).click();
        ExplicitWaitToClick(clearSearch);
        driver.findElement(clearSearch).click();
        return this;
    }

    public ConTactPage addDepartment(String depName, String subDep) {
        driver.findElement(By.cssSelector(".member_colLeft_top_addBtn")).click();
        driver.findElement(addDep).click();
        driver.findElement(By.name("name")).sendKeys(depName);
        driver.findElement(By.linkText("选择所属部门")).click();
        
        return this;
    }

    public static void GetDriver() {
        driver = MainPage.driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void ExplicitWaitToClick(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
