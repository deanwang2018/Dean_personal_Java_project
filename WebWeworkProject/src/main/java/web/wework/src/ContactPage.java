package web.wework.src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/14
 * @time 15:21
 */
public class ContactPage extends WebWeworkHelperFactory {
    //    上层操作栏定位符变量
//    todo: 后期考虑多语言一次定位，目前只支持中文
    public static By topMemberAdd = By.linkText("添加成员");
    public static By topBatchImportExport = By.linkText("批量导入/导出");
    public static By topSetDepart = By.linkText("设置所在部门");
    public static By topDelete = By.linkText("删除");
    public static By topInvite = By.linkText("微信邀请");

    //    添加成员定位符变量
    public static By userName = new By.ByCssSelector("#username");
    public static By aliasName = new By.ByCssSelector("#memberAdd_english_name");
    public static By acctNumber = new By.ByCssSelector("#memberAdd_acctid");
    public static By phoneNumber = new By.ByCssSelector("#memberAdd_phone");
    //    public static By save = new By.ByCssSelector(".member_colRight_operationBar:nth-child(3) > .js_btn_save");
    public static By save = By.linkText("保存");
    public static By search = new By.ByCssSelector("#memberSearchInput");
    public static By clearSearch = new By.ByCssSelector("#clearMemberSearchInput");
    //po原则2 不要暴露页面内部实现细节
    private static By departInfo = By.cssSelector(".js_party_info");
    private By memberInfo = By.cssSelector(".member_display_cover_detail_name");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage clickAddMember() {
        click(topMemberAdd);
        return this;
    }

    public static void AddMember(String username, String acctid, String mobile) {
//        todo: 后期增加所有要测试的输入域，判断为空时的默认处理，或者增加新方法添加非必填项
        waitAndClick(userName);
        sendKeys(userName, username);
        sendKeys(acctNumber, acctid);
        sendKeys(phoneNumber, mobile);
        click(save);
    }

    public ContactPage addDepart(String departName) {
        click(By.cssSelector(".member_colLeft_top_addBtn"));
//        click(By.linkText("添加"));
        click(By.cssSelector(".js_create_party"));
        sendKeys(By.name("name"), departName);
        click(By.cssSelector(".js_parent_party_name"));
        waitAndClick(By.linkText("科技部"));
        click(By.linkText("确定"));
        return this;
    }

    public static void search(String value) {
        sendKeys(search, value);
    }

    public String searchMemberContext(String memberName) {
        search(memberName);
        String content = getByInfo(memberInfo);
        return content;
    }

    public ContactPage searchDepart(String departName) {
        search(departName);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public String getByInfo(By by) {
        String content = driver.findElement(by).getText();
        System.out.println(content);
        return content;
    }

    public static String getPartyInfo() {
        String content = driver.findElement(departInfo).getText();
        System.out.println(content);
        return content;
    }

    public static void clearAllDeparts(String departName) {
        //todo
        search(departName);
        if(getPartyInfo().contains("无任何成员")){
            // delete
        } else{
            waitAndClick(By.cssSelector(".member_colRight_memberTable_th_Checkbox"));
            click(topDelete);
            waitAndClick(By.linkText("确认"));
//            driver.switchTo().alert().accept();
            // delete
        }
    }

    public static void clearMember(String memberName){
        search(memberName);
        waitAndClick(By.linkText("删除"));
        waitAndClick(By.linkText("确认"));
    }
}
