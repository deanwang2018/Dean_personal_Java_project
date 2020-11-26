package app.wework.pageobject;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author wangdian
 * @package app.wework.src
 * @date 2020/11/19
 * @time 11:31
 */
public class ContactPage extends BasePage {

    public List<HashMap<String, JSONObject>> contact;

    public ContactPage(AppiumDriver driver) throws IOException {
        super(driver);
        contact = getElement();
    }

    public JSONObject getLocator(String type) {
        JSONObject locator = new JSONObject();
        for (int i = 0; i < contact.size(); i++) {
            if (contact.get(i).toString().contains(type)) {
                locator = contact.get(i).get(type);
                break;
            }
        }
        return locator;
    }

    public ContactPage addMemberManually(String name, String mobile, String email, String address) {
        click(byId(getLocator("id").get("manage").toString()));
        click(byText(getLocator("text").get("addmember").toString()));
        click(byText(getLocator("text").get("manualadd").toString()));

        sendKeys(byXpath(getLocator("xpath").get("name").toString()), name);
        sendKeys(byId(getLocator("id").get("mobile").toString()), mobile);
        sendKeys(byXpath(getLocator("xpath").get("email").toString()), email);
        click(byXpath(getLocator("xpath").get("address").toString()));
        sendKeys(byId(getLocator("id").get("addressinput").toString()), address);
        click(byText(getLocator("text").get("confirm").toString()));
        click(byText(getLocator("text").get("save").toString()));

//        退出到通讯录界面
        click(byId(getLocator("id").get("back").toString()));
        click(byId(getLocator("id").get("closemanage").toString()));

        return this;
    }

    public ContactPage searchAndUpdateMember(String searchMemberName, String updateMemberName, String email, String address) throws InterruptedException {
        if (isSearchExist(searchMemberName)) {
            clickTextView(searchMemberName);
            click(byId(getLocator("id").get("threedot").toString()));
            click(byText(getLocator("text").get("editmember").toString()));
            if (updateMemberName == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(getLocator("xpath").get("name").toString())).getText());
            } else {
                sendKeys(byXpath(getLocator("xpath").get("name").toString()), updateMemberName);
            }

            if (email == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(getLocator("xpath").get("email").toString())).getText());
            } else {
                sendKeys(byXpath(getLocator("xpath").get("email").toString()), email);
            }

            if (address == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(getLocator("xpath").get("address").toString())).getText());
            } else {
                click(byXpath(getLocator("xpath").get("address").toString()));
                sendKeys(byId(getLocator("id").get("addressinput").toString()), address);
                click(byText(getLocator("text").get("confirm").toString()));
                click(byText(getLocator("text").get("save").toString()));
                click(byId(getLocator("id").get("back").toString()));
                click(byId(getLocator("id").get("back").toString()));
            }
        } else {
            System.out.println("没有搜索到: " + searchMemberName);
        }

        return this;
    }

    public ContactPage searchAndDeleteMember(String memberName) throws InterruptedException {
        if (isSearchExist(memberName)) {
            clickTextView(memberName);
            click(byId(getLocator("id").get("threedot").toString()));
            click(byText(getLocator("text").get("editmember").toString()));
            click(byText(getLocator("text").get("deletemember").toString()));
            click(byText(getLocator("text").get("confirm").toString()));
            click(byId(getLocator("id").get("back").toString()));
        } else {
            System.out.println("没有搜索到: " + memberName);
        }

        return this;
    }

    public boolean isSearchExist(String content) throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            search(content);
//        确保搜索结果出现
            timeout(getLocator("xpath").get("textview").toString(), 10);

            driver.findElements(byXpath(getLocator("xpath").get("textview").toString())).stream().forEach(v -> {
                        if (v.getText().contains(content) && !v.getText().contains("网络查找手机/邮箱")) {
                            flag.set(true);
                        }
                    }
            );
        } finally {
            returnFirstPage();
        }
        return flag.get();
    }

    public String getCurrentTextView() throws InterruptedException {
        StringBuilder viewValue = new StringBuilder();
        timeout(getLocator("xpath").get("textview").toString(), 10);
        driver.findElements(byXpath(getLocator("xpath").get("textview").toString())).stream().forEach(v -> {
                    viewValue.append(v.getText());
                }
        );
        return viewValue.toString();
    }

    /*
    getType: id, tag, text, attribute, cssValue
    value: attribute name or css propertyName
     */
    public String getCurrentTextView(String getType, String value) throws InterruptedException {
        StringBuilder viewValue = new StringBuilder();
        timeout(getLocator("xpath").get("textview").toString(), 10);
        driver.findElements(byXpath(getLocator("xpath").get("textview").toString())).stream().forEach(v -> {
                    if (getType.contains("id")) {
                        viewValue.append(v.getId());
                    }
                    if (getType.contains("tag")) {
                        viewValue.append(v.getTagName());
                    }
                    if (getType.contains("text")) {
                        viewValue.append(v.getText());
                    }
                    if (getType.contains("attribute")) {
                        viewValue.append(v.getAttribute(value));
                    }
                    if (getType.contains("cssValue")) {
                        viewValue.append(v.getCssValue(value));
                    }
                }
        );
        return viewValue.toString();
    }

    public ContactPage getMemberInfo(String memberName) throws InterruptedException {
        if (isSearchExist(memberName)) {
            clickTextView(memberName);
            click(byId(getLocator("id").get("threedot").toString()));
            click(byText(getLocator("text").get("editmember").toString()));

        } else {
            System.out.println("没有搜索到: " + memberName);
        }

        return this;
    }

    public void clickTextView(String text) {
        try {
            driver.findElements(byXpath(getLocator("xpath").get("textview").toString())).stream().forEach(v -> {
                        if (v.getText().contains(text)) {
                            v.click();
                            // 退出forEach
                            throw new Error("EndIterative");
                        }
                    }
            );
        } catch (Error e) {
            if (!e.getMessage().contains("EndIterative")) {
                throw e;
            }
        }
    }

    public void timeout(String classValue, int timeout) throws InterruptedException {
        while (driver.findElements(byXpath(classValue)).size() <= 1 && timeout > 0) {
            Thread.sleep(1000);
            timeout = timeout - 1;
            System.out.println("sleep left: " + timeout);
        }
    }

    public void returnFirstPage() throws InterruptedException {
        int count = 10;
//        System.out.println(getCurrentTextView("attribute", "resource-id") + "\n");
        while (getCurrentTextView("attribute", "resource-id").
                contains(getLocator("id").get("back").toString()) && count > 0) {
            click(byId(getLocator("id").get("back").toString()));
            timeout(getLocator("xpath").get("textview").toString(), 10);
            if (getCurrentTextView("attribute", "resource-id").
                    contains(getLocator("id").get("closemanage").toString())) {
                click(byId(getLocator("id").get("closemanage").toString()));
            }
            if (getCurrentTextView("text", "").
                    contains(getLocator("text").get("cancel").toString())) {
                click(byText(getLocator("text").get("cancel").toString()));
            }
            count = count - 1;
        }

        while (getCurrentTextView("attribute", "resource-id").
                contains(getLocator("id").get("closemanage").toString()) && count > 0) {
            click(byId(getLocator("id").get("closemanage").toString()));
            count = count - 1;
        }
    }

    public ContactPage addDepart(String highLevelDept, String departName) throws InterruptedException {
//        先搜索判断上级部门和要添加的部门是否存在
        assertTrue(isSearchExist(highLevelDept), "上级部门 '" + highLevelDept + "' 不存在！");
        assertFalse(isSearchExist(departName), "'" + departName + "' 部门已存在，无法添加！");

//        获取上级部门的部门结构
        String[] deptStruct = getDepartStructure(highLevelDept);
        int len = deptStruct.length;

        if (len >= 2) {
            //        点击上级部门的前继
            for (int i = 0; i < len; i++) {
                click(byText(deptStruct[i]));
            }
            clickAndAddDepart(highLevelDept, departName);
        } else {
//            判断上级部门是否在当前页，不在需要滚动寻找
            if (deptStruct[0].contains(getLocator("text").get("companyname").toString())) {
                ScrollToTextAndClick(highLevelDept, false);
                clickAndAddDepart(highLevelDept, departName);
            } else {
                ScrollToTextAndClick(deptStruct[0], true);
                clickAndAddDepart(highLevelDept, departName);
            }
        }
        returnFirstPage();
        returnFirstPage();

        return this;
    }

    public ContactPage updateDepartName(String OldDepart, String NewDepart) throws InterruptedException {
//        先搜索判断上级部门和要添加的部门是否存在
        assertTrue(isSearchExist(OldDepart), "原部门 '" + OldDepart + "' 不存在！");
        assertFalse(isSearchExist(NewDepart), "'" + NewDepart + "' 已存在，无法修改！");

//        获取上级部门的部门结构
        String[] deptStruct = getDepartStructure(OldDepart);
        int len = deptStruct.length;

        if (len >= 2) {
            //        点击上级部门的前继
            for (int i = 0; i < len; i++) {
                click(byText(deptStruct[i]));
            }
            clickAndUpdateDepartName(OldDepart, NewDepart);
        } else {
//            判断上级部门是否是公司
            if (deptStruct[0].contains(getLocator("text").get("companyname").toString())) {
                ScrollToTextAndClick(OldDepart, false);
                clickAndUpdateDepartName(OldDepart, NewDepart);
            } else {
                ScrollToTextAndClick(deptStruct[0], true);
                clickAndUpdateDepartName(OldDepart, NewDepart);
            }
        }
        returnFirstPage();
        returnFirstPage();

        return this;
    }

    public ContactPage deleteDepartName(String departName) throws InterruptedException {
//        判断要删除的部门是否可删
        search(departName);
        click(byId(getLocator("id").get("deptstruct").toString()));
        assertTrue(getCurrentTextView().contains("部门无成员"),
                "部门 '" + departName + "' 下有成员或部门下还有部门无法删除！");

        returnFirstPage();

        String[] deptStruct = getDepartStructure(departName);
        int len = deptStruct.length;

        if (len >= 2) {
            //        点击前继部门
            for (int i = 0; i < len; i++) {
                ScrollToTextAndClick(deptStruct[i], true);
            }
            clickAndDeleteDepartName(departName);
        } else {
//            判断上级部门是否是公司
            if (deptStruct[0].contains(getLocator("text").get("companyname").toString())) {
                ScrollToTextAndClick(departName, false);
                clickAndDeleteDepartName(departName);
            } else {
                ScrollToTextAndClick(deptStruct[0], true);
                clickAndDeleteDepartName(departName);
            }
        }
        returnFirstPage();
        returnFirstPage();

        return this;
    }

    public void clickAndAddDepart(String highLevelDept, String departName) {
//        点击上级部门,点击管理按钮,点击添加子部门,输入部门名称,点击确定
        clickSendConfirm(departName, byText(getLocator("text").get("senddeptname").toString()),
                byText(highLevelDept),
                byId(getLocator("id").get("manage").toString()),
                byText(getLocator("text").get("addsubdept").toString()));
    }

    public void clickAndUpdateDepartName(String OldDept, String NewDept) {
//        点击旧部门,点击管理按钮,点击更多管理，点击修改当前部门名称,输入新部门名称,点击确定
        clickSendConfirm(NewDept, byXpath(getLocator("xpath").get("EditText").toString()),
                byText(OldDept),
                byId(getLocator("id").get("manage").toString()),
                byText(getLocator("text").get("moremanage").toString()),
                byText(getLocator("text").get("updatecurrentdept").toString()));
    }

    public void clickAndDeleteDepartName(String departName) {
        seriesClick(byText(departName),
                byId(getLocator("id").get("manage").toString()),
                byText(getLocator("text").get("moremanage").toString()),
                byText(getLocator("text").get("deletedept").toString()),
                byText(getLocator("text").get("confirm").toString()));
    }

    public void clickSendConfirm(String sendKeys, By sendBy, By... by) {
        seriesClick(by);
        sendKeys(sendBy, sendKeys);
        click(byText(getLocator("text").get("confirm").toString()));
    }

    public void seriesClick(By... by) {
        for (int i = 0; i < by.length; i++) {
            click(by[i]);
        }
    }


    public String[] getDepartStructure(String departName) throws InterruptedException {
        String[] deptStruct;
        try {
            search(departName);
            deptStruct = driver.findElement(byId(getLocator("id").
                    get("deptstruct").toString())).getText().split("/");
        } finally {
            returnFirstPage();
        }

        return deptStruct;
    }
}
