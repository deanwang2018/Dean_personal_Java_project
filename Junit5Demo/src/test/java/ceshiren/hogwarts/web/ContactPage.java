package ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/14
 * @time 15:21
 */
public class ContactPage extends WebWeworkHelperFactory {
    public static By userName = new By.ByCssSelector("#username");
    public static By aliasName = new By.ByCssSelector("#memberAdd_english_name");
    public static By acctNumber = new By.ByCssSelector("#memberAdd_acctid");
    public static By phoneNumber = new By.ByCssSelector("#memberAdd_phone");
    //    public static By save = new By.ByCssSelector(".member_colRight_operationBar:nth-child(3) > .js_btn_save");
    public static By save = By.linkText("保存");
    public static By search = new By.ByCssSelector("#memberSearchInput");
    //po原则2 不要暴露页面内部实现细节
    private By departInfo=By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }

    public ContactPage addDepart(String departName){
        click(By.cssSelector(".member_colLeft_top_addBtn"));
//        click(By.linkText("添加"));
        click(By.cssSelector(".js_create_party"));
        sendKeys(By.name("name"),departName);
        click(By.cssSelector(".js_parent_party_name"));
        click(By.linkText("科技部"));
        click(By.linkText("确定"));
        return this;
    }

    public ContactPage searchDepart(String departName){
        sendKeys(search,departName);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public String getPartyInfo(){
        String content = driver.findElement(departInfo).getText();
        System.out.println(content);
        return content;
    }

    public void clearAllDeparts(){
        //todo
    }
}
