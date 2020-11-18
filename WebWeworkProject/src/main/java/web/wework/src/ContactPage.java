package web.wework.src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    //    确认删除定位变量
    public static By confirm = By.linkText("确认");
    public static By yes = By.linkText("确定");

    public static By judgeClickable = new By.ByCssSelector(".ww_icon_AddMember");

//    定位左侧导航栏小箭头
//    $x('//li[@role="treeitem"]//a[contains(text(),"科技部")]/preceding-sibling::i[1]')

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
    private By deptInfo = By.cssSelector("#party_name");

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
        waitAndClick(save);
    }

    public ContactPage addDepart(String departName) throws InterruptedException {
        waitAndClick(By.cssSelector(".member_colLeft_top_addBtn"));
//        click(By.linkText("添加"));
        click(By.cssSelector(".js_create_party"));
        sendKeys(By.name("name"), departName);
        waitAndClick(By.cssSelector(".js_parent_party_name"));
//        waitAndClick(By.linkText("科技部"), 1);
        driver.findElement(By.xpath("//div[@class=\"member_tag_dialog_inputDlg\"]//a[contains(text(),\"科技部\")]")).click();

        waitAndClick(By.linkText("确定"));
        waitFadOut(By.id("js_tips"), 10);
        return this;
    }

    public ContactPage updateDepartName(String oldDepartName, String newDepartName) {
        search(oldDepartName);
        click(judgeClickable);
        waitAndClick(By.linkText("修改名称"));
        sendKeys(By.name("name"), newDepartName);
        click(save);
        return this;
    }

    public ContactPage addMemberInDepart(String departName, String memberName, String acctid, String mobile) throws InterruptedException {
        search(departName);
        click(judgeClickable);
        click(By.linkText("添加成员"));
        AddMember(memberName, acctid, mobile);
        waitFadOut(By.id("js_tips"), 10);
        return this;
    }

    public ContactPage deleteMember(String memberName) throws InterruptedException {
        if (searchMemberContext(memberName).equals(memberName)) {
            waitAndClick(topDelete);
            waitAndClick(confirm);
            waitFadOut(By.id("js_tips"), 10);
            System.out.println("complete delete member: " + memberName);
        } else {
            System.out.println("member: " + memberName + "not there!");
        }
        return this;
    }

    public ContactPage deleteDepart(String departName) throws InterruptedException {
        actions = new Actions(driver);
        String[] departStruct = searchDeptContext(departName).split("/");
        int len = departStruct.length;
        // 清除搜索输入
        search("");
        for (int i = 0; i < len - 1; i++) {
            waitAndClick(By.xpath("//li[@role=\"treeitem\"]//a[contains(text(),\"" + departStruct[i] + "\")]/preceding-sibling::i[1]"));
        }
        click(By.linkText(departName));
        actions.moveToElement(driver.findElement(By.xpath("//li[@role=\"treeitem\"]//a[contains(text(),\"" + departName + "\")]/span[1]"))).click();
        actions.perform();
        waitAndClick(topDelete);
        waitAndClick(yes);
        waitFadOut(By.id("js_tips"), 10);
        System.out.println("complete delete member: " + departName);

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

    public String searchDeptContext(String departName) {
        search(departName);
        String content = getByInfo(deptInfo);
        return content;
    }

    public ContactPage searchDepart(String departName) {
        search(departName);
        click(judgeClickable);
        return this;
    }

    public String getByInfo(By by) {
        String content = driver.findElement(by).getText();
        System.out.println(content);
        return content;
    }

    public String getPartyInfo() {
        String content = driver.findElement(departInfo).getText();
        System.out.println(content);
        return content;
    }

    public static void clearAllDeparts(String departName) {
        //todo
//        search(departName);
//        if (getByInfo().contains("无任何成员")) {
////             delete
//        } else {
//            waitAndClick(By.cssSelector(".member_colRight_memberTable_th_Checkbox"));
//            click(topDelete);
//            waitAndClick(confirm);
//            driver.switchTo().alert().accept();
////             delete
//        }
    }

    public static void clearMember(String memberName) throws InterruptedException {
        search(memberName);
        waitAndClick(topDelete);
        waitAndClick(confirm);
        waitFadOut(By.id("js_tips"), 10);

    }
}
