package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 11:43
 */
public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    @BeforeAll
    static void initData() {
        String browserName = System.getenv("browser");
//        browserName = "chrome";
        if ("chrome".equals(browserName)) {
            System.setProperty("Webdriver.chrome.driver", "D:\\Dean_working_dir\\working_software_target\\chromedriver_win32");
            driver = new ChromeDriver();
        } else if ("firefox".equals(browserName)) {
            System.setProperty("webdriver.gecko.driver", "D:\\Dean_working_dir\\working_software_target\\geckodriver-v0.27.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else if("edge".equals(browserName)){
            System.setProperty("webdriver.edge.driver", "D:\\Dean_working_dir\\working_software_target\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

//        actions = new Actions(driver);
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver,3);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
