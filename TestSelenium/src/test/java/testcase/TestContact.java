package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.MainPage;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/13
 * @time 9:28
 */
public class TestContact {
    @Test
    void testAddMember(){
        MainPage main = new MainPage();
        main.toContact().addMember("Ethan","Ethan20201013","18610061672");

        // todo: assert
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
