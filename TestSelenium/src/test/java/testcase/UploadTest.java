package testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/10
 * @time 15:44
 */
public class UploadTest extends BaseTest{

    @Test
    void uploadTest(){
        try {
            driver.get("https://www.baidu.com/");
            driver.findElement(By.xpath("//span[@class='soutu-btn']")).click();

            Thread.sleep(4000);

            driver.findElement(By.xpath("//input[@class='upload-pic']")).sendKeys("D:\\tmp\\1.jpg");

            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
