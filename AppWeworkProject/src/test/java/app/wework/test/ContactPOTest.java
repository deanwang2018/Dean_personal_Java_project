package app.wework.test;

import app.wework.src.BasePage;
import app.wework.src.ContactPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author wangdian
 * @package app.wework.test
 * @date 2020/11/19
 * @time 12:15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactPOTest {
    //    private static BasePage basePage;
    private static ContactPage contactPage;

    @BeforeEach
    void beforeEach() throws IOException {
        contactPage = new ContactPage();
    }

    @Order(1)
    @Test
    void AddMemberManuallyTest() throws InterruptedException {
        assertTrue(contactPage.addMemberManually("Ethan", "18611111111",
                "123456789@qq.com", "腾讯大厦").isSearchExist("Ethan"));
    }

    @Order(2)
    @Test
    void SearchAndUpdateMemberTest() throws MalformedURLException, InterruptedException {
        assertTrue(contactPage.searchAndUpdateMember("Ethan", null, null,
                "百度大厦").getMemberInfo("Ethan").getCurrentTextView().contains("百度大厦"));
    }

    @Order(3)
    @Test
    void DeleteMemberTest() throws InterruptedException {
        assertTrue(!contactPage.searchAndDeleteMember("Ethan").isSearchExist("Ethan"));
    }
}
