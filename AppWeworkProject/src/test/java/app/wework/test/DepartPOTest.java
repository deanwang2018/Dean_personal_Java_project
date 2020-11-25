package app.wework.test;

import app.wework.pageobject.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author wangdian
 * @package app.wework.test
 * @date 2020/11/21
 * @time 16:46
 */
public class DepartPOTest {
    protected static MainPage mainPage;

    @BeforeAll
    static void beforeAll() throws IOException {
        mainPage = new MainPage();
    }

    @Test
    void addDepart() throws IOException, InterruptedException {
        assertTrue(mainPage.contact().addDepart("科技13部", "接口测试部").isSearchExist("接口测试部"));
    }

    @Test
    void updateDepart() {

    }

    @Test
    void deleteDepart() {

    }
}
