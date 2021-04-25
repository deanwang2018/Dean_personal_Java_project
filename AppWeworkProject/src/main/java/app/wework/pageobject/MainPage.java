package app.wework.pageobject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author wangdian
 * @package app.wework.src
 * @date 2020/11/19
 * @time 11:30
 */
public class MainPage extends BasePage {

    public MainPage() throws MalformedURLException {
        super("com.tencent.wework", ".launch.WwMainActivity");
    }

    public ContactPage contact() throws IOException {
        click(byText("通讯录"));
        return new ContactPage(driver);
    }

    public ContactPage staticContact() throws IOException {
        return new ContactPage(driver);
    }
}
