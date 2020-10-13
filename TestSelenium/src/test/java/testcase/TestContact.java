package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.ConTactPage;
import page.MainPage;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/13
 * @time 9:28
 */
public class TestContact {
    static MainPage main;
    static ConTactPage contact;

    @BeforeAll
    static void setupEnv() {
        main = new MainPage();
        contact = main.toContact();
    }

    @Test
    void testAddMember() {
        contact.addMember("2", "2", "12345678901");

        // todo: assert
    }

    @Test
    void testSearch() {
        contact.search("2").delete();
    }

    @AfterAll
    static void tearDown() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainPage.driver.quit();
    }
}
