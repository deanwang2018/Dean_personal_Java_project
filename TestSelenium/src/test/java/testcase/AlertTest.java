package testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 15:54
 */
public class AlertTest extends BaseTest{

    @Test
    void alertTest(){
        try {
            driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");

            driver.switchTo().frame("iframeResult");

            Actions actions = new Actions(driver);
            actions.dragAndDrop(driver.findElement(By.id("draggable")),driver.findElement(By.id("droppable")));
            actions.perform();

            Thread.sleep(5000);

            driver.switchTo().alert().accept();

            driver.switchTo().parentFrame();

            System.out.println(driver.findElement(By.id("submitBTN")).getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
