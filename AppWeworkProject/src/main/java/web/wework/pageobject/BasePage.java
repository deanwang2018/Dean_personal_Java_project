package web.wework.pageobject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.framework.DriverPage;

import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package web.wework.pageobject
 * @date 2020/11/25
 * @time 22:27
 */
public class BasePage extends DriverPage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage(String driverType) {
        switch (driverType) {
            case "chrome":
//                System.setProperty("Webdriver.chrome.driver", "yourpath");
                driver = new ChromeDriver();
            case "firefox":
//                System.setProperty("webdriver.gecko.driver", "yourpath");
                driver = new FirefoxDriver();
            case "edge":
//                System.setProperty("webdriver.edge.driver", "yourpath");
                driver = new EdgeDriver();
            default:
//                System.setProperty("Webdriver.chrome.driver", "yourpath");
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        selenium 4.0 use duration
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, 10);

    }
}
