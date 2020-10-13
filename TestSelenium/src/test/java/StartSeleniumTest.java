import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author wangdian
 * @package PACKAGE_NAME
 * @date 2020/10/9
 * @time 9:58
 */
public class StartSeleniumTest {

    @Test
    public void startSelenium() {
        WebDriver wd = new ChromeDriver();

//        System.setProperty("Webdriver.chrome.driver",
//                "D:\\Dean_working_dir\\working_software_target\\chromedriver_win32");
//        wd.findElement(By.cssSelector(".d-button-label")).click();
        wd.get("https://home.testing-studio.com");

        wd.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }
}
