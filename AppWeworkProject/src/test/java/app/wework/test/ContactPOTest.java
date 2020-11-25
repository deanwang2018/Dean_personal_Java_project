package app.wework.test;

import app.wework.pageobject.ContactPage;
import app.wework.pageobject.MainPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * @author wangdian
 * @package app.wework.test
 * @date 2020/11/19
 * @time 12:15
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactPOTest {
    protected static MainPage mainPage;

    @BeforeAll
    static void beforeAll() throws IOException {
        mainPage = new MainPage();
        mainPage.contact();
    }

    @BeforeEach
    void beforeEach() throws InterruptedException, IOException {
        mainPage.staticContact().returnFirstPage();
    }

    @Order(1)
    @Test
    void AddMemberManuallyTest() throws InterruptedException, IOException {
        assertTrue(mainPage.staticContact().addMemberManually("Ethan", "18611111111",
                "123456789@qq.com", "腾讯大厦").isSearchExist("Ethan"));
    }

    @Order(2)
    @Test
    void SearchAndUpdateMemberTest() throws IOException, InterruptedException {
        assertTrue(mainPage.staticContact().searchAndUpdateMember("Ethan", null, null,
                "百度大厦").getMemberInfo("Ethan").getCurrentTextView().contains("百度大厦"));
    }

    @Order(3)
    @Test
    void DeleteMemberTest() throws InterruptedException, IOException {
        assertTrue(!mainPage.staticContact().searchAndDeleteMember("Ethan").isSearchExist("Ethan"));
    }
}
