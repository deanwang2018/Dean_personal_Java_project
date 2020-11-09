package junit5;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author wangdian
 * @package junit5
 * @date 2020/9/30
 * @time 16:04
 */
public class AllureTest {

    @Test
    @Tag("Allure测试")
    @Link(name = "Dean's github", type = "deanlink", url = "https://github.com/deanwang2018/Dean_personal_Java_project")
    @Description("Description")
    @DisplayName("DisplayName 测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://ceshiren.com/t/topic/7718")
    void allureTest() {
        Allure.step("step1");
        System.out.println("allure test step1");
        Allure.step("step2");
        System.out.println("allure test step2");
        Allure.addAttachment("content1", "test");
        Allure.addAttachment("pic", "image/png",
                this.getClass().getResourceAsStream("/1.jpg"), ".jpg");
    }
}
