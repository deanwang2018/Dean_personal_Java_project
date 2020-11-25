package app.wework.pageobject;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        search(content);
//        确保搜索结果出现
        timeout(getLocator("xpath").get("textview").toString(), 10);

        driver.findElements(byXpath(getLocator("xpath").get("textview").toString())).stream().forEach(v -> {
                    if (v.getText().contains(content)) {
                        flag.set(true);
                    }
                }
        );

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
//        先搜索判断上级部门存在，再搜索判断要添加的部门不存在，在屏幕上寻找上级部门
//        if (isSearchExist(highLevelDept)) {
//            returnFirstPage();
//            System.out.println("即将在 '" + highLevelDept + "' 下添加子部门");
//        } else {
//            throw new Error("上级部门 '" + highLevelDept + "' 不存在！");
//        }
//
//        if (!isSearchExist(departName)) {
//            returnFirstPage();
//            System.out.println("即将在 '" + highLevelDept + "' 下添加子部门 '" + departName + "'");
//        } else {
//            throw new Error("'" + departName + "' 已存在，无法添加！");
//        }

//        获取上级部门的部门结构
        search(highLevelDept);
        String[] deptStruct = driver.findElement(byId(getLocator("id").
                get("deptstruct").toString())).getText().split("/");
        int len = deptStruct.length;

        if (len >= 2) {
            //        点击上级部门的前继
            for (int i = 0; i < len; i++) {
                click(byText(deptStruct[i]));
            }
            clickAndAddDepart(highLevelDept, departName);
        } else {
            returnFirstPage();
//            判断上级部门是否在当前页，不在需要滚动寻找
            if (deptStruct[0].contains(getLocator("text").get("companyname").toString())) {
                ScrollToTextAndClick(highLevelDept);
                clickAndAddDepart(highLevelDept, departName);
            } else {
                ScrollToTextAndClick(deptStruct[0]);
                clickAndAddDepart(highLevelDept, departName);
            }
        }

//        点击管理按钮的关闭按钮
        click(byId(getLocator("id").get("closemanage").toString()));
//        点击后退按钮
        click(byId(getLocator("id").get("back").toString()));

        return this;
    }

    public void clickAndAddDepart(String highLevelDept, String departName) {
//        点击上级部门,点击管理按钮,点击添加子部门,输入部门名称,点击确认
        click(byText(highLevelDept));
        click(byId(getLocator("id").get("manage").toString()));
        click(byText(getLocator("text").get("addsubdept").toString()));
        sendKeys(byText(getLocator("text").get("senddeptname").toString()), departName);
        click(byText(getLocator("text").get("confirm").toString()));
    }
}
