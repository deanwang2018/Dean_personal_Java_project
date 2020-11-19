package app.wework.src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangdian
 * @package app.wework.src
 * @date 2020/11/19
 * @time 11:31
 */
public class ContactPage extends BasePage {
    //    todo: 参数化
    public String addMemberIcon = "添加成员";
    public String manualAddIcon = "手动输入添加";
    public String nameCode = "//*[@resource-id='com.tencent.wework:id/ehw']/descendant::android.widget.EditText";
    public String mobileId = "fiv";
    public String emailCode = "//*[@resource-id='com.tencent.wework:id/eh_']/descendant::android.widget.EditText";
    public String addressCode = "//*[@resource-id='com.tencent.wework:id/egn']/descendant::android.widget.TextView";
    public String addressInputId = "it";
    public String backIcon = "hxb";
    public String confirm = "确定";
    public String save = "保存";
    public String textView = "//*[@class='android.widget.TextView']";
    public String threeDotId = "hxm";
    public String editMember = "编辑成员";
    public String deleteMember = "删除成员";

    public ContactPage() throws MalformedURLException {
        click(byText(contactIcon));
    }

    public AppiumDriver<MobileElement> ContactPage() {
        return BasePage.driver;
    }

    public ContactPage addMemberManually(String name, String mobile, String email, String address) {
        click(byText(addMemberIcon));
        click(byText(manualAddIcon));

        sendKeys(byXpath(nameCode), name);
        sendKeys(byId(mobileId), mobile);
        sendKeys(byXpath(emailCode), email);
        click(byXpath(addressCode));
        sendKeys(byId(addressInputId), address);
        click(byText(confirm));
        click(byText(save));

//        退出到通讯录界面
        click(byId(backIcon));

        return this;
    }

    public ContactPage searchAndUpdateMember(String searchMemberName, String updateMemberName, String email, String address) throws InterruptedException {
        if (isSearchExist(searchMemberName)) {
            clickTextView(searchMemberName);
            click(byId(threeDotId));
            click(byText(editMember));
            if (updateMemberName == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(nameCode)).getText());
            } else {
                sendKeys(byXpath(nameCode), updateMemberName);
            }

            if (email == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(emailCode)).getText());
            } else {
                sendKeys(byXpath(emailCode), email);
            }

            if (address == null) {
                System.out.println("not change for: " + driver.findElement(byXpath(addressCode)).getText());
            } else {
                click(byXpath(addressCode));
                sendKeys(byId(addressInputId), address);
                click(byText(confirm));
                click(byText(save));
                click(byId(backIcon));
                click(byId(backIcon));
            }
        } else {
            System.out.println("没有搜索到: " + searchMemberName);
        }

        return this;
    }

    public ContactPage searchAndDeleteMember(String memberName) throws InterruptedException {
        if (isSearchExist(memberName)) {
            clickTextView(memberName);
            click(byId(threeDotId));
            click(byText(editMember));
            click(byText(deleteMember));
            click(byText(confirm));
            click(byId(backIcon));
        } else {
            System.out.println("没有搜索到: " + memberName);
        }

        return this;
    }

    public boolean isSearchExist(String content) throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(false);
        search(content);
//        确保搜索结果出现
        timeout(textView,10);

        driver.findElements(byXpath(textView)).stream().forEach(v -> {
                    if (v.getText().contains(content)) {
                        flag.set(true);
                    }
                }
        );

        return flag.get();
    }

    public String getCurrentTextView() throws InterruptedException {
        StringBuilder viewValue = new StringBuilder();
        timeout(textView,10);
        driver.findElements(byXpath(textView)).stream().forEach(v -> {
                    viewValue.append(v.getText());
                }
        );
        return viewValue.toString();
    }

    public ContactPage getMemberInfo(String memberName) throws InterruptedException {
        if (isSearchExist(memberName)) {
            clickTextView(memberName);
            click(byId(threeDotId));
            click(byText(editMember));

        } else {
            System.out.println("没有搜索到: " + memberName);
        }

        return this;
    }

    public void clickTextView(String text) {
        try {
            driver.findElements(byXpath(textView)).stream().forEach(v -> {
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
}
