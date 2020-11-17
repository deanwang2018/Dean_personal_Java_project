package web.wework.src;

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
//    首页导航栏通过id定位变量
    public static By menuContact = new By.ByCssSelector("#menu_contacts");
    public static By menuAppManagement = new By.ByCssSelector("#menu_apps");
    public static By menuCustomerContact = new By.ByCssSelector("#menu_customer");
    public static By menuManagementTools = new By.ByCssSelector("#menu_manageTools");
    public static By menuMyCompany = new By.ByCssSelector("#menu_profile");

//    常用入口通过node-type定位变量
    public static By addMember = By.cssSelector("[node-type=addmember]");
    public static By importContact = By.cssSelector("[node-type=import]");
    public static By memberJoin = By.cssSelector("[node-type=memberJoin]");
    public static By messageBroadcast = By.cssSelector("[node-type=message]");
    public static By customerContact = By.cssSelector("[node-type=wxplugin]");
    public static By attendance = By.cssSelector("[node-type=attendance]");

    void setupEnv() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
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

//    首页导航栏
    public ContactPage contact(){
        click(menuContact);
        return new ContactPage(driver);
    }

    public AppManagementPage AppManagement(){
        click(menuAppManagement);
        return new AppManagementPage(driver);
    }

    public CustomerContactPage CustomerContact(){
        click(menuContact);
        return new CustomerContactPage(driver);
    }

    public ManagementToolsPage ManagementTools(){
        click(menuManagementTools);
        return new ManagementToolsPage(driver);
    }

    public MyCompanyPage MyCompany(){
        click(menuMyCompany);
        return new MyCompanyPage(driver);
    }

//    常用入口
    public void AddMember(String username, String acctid, String mobile){
        click(addMember);
        ContactPage.AddMember(username,acctid,mobile);
    }
}
