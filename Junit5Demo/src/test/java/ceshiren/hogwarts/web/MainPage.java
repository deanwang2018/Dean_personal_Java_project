package ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/14
 * @time 15:20
 */
public class MainPage extends WebWeworkHelperFactory {
//    public static By member = By.xpath("//span[contains(.,'添加成员')]");
//    public static By member = By.linkText("添加成员");
    public static By member = By.cssSelector("[node-type=addmember]");
    public static By menuContact = new By.ByCssSelector("#menu_contacts");

    void setupEnv() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        url = "https://work.weixin.qq.com/wework_admin/frame";

        if(!file.exists()){
            getCookies();
        } else{
            addCookiesAndLogin();
        }
    }

    public MainPage() throws IOException, InterruptedException {
        this.setupEnv();
    }

    public ContactPage contact(){
        click(menuContact);
        return new ContactPage(driver);
    }
}
