package web.wework.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.wework.src.ContactPage;
import web.wework.src.MainPage;
import web.wework.src.WebWeworkHelperFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author wangdian
 * @package web.wework.test
 * @date 2020/11/14
 * @time 15:25
 */
public class ContactPOTest extends WebWeworkHelperFactory {
    private static MainPage mainPage;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        // 打开页面
        // 复用session登录
        mainPage = new MainPage();
//        mainPage.contact().clearAllDeparts();
    }

    @Test
    void testMainPageAddMember() throws IOException, InterruptedException {
        mainPage.AddMember("test1","18611111111","18611111111");
        assertTrue(mainPage.contact().searchMemberContext("test1").contains("test1"));
    }

    @Test
    void departmentSearch() throws IOException, InterruptedException {
        assertTrue(new MainPage().contact().searchDepart("测试开发部").getPartyInfo().contains("无任何成员"));
    }

    //    todo: 部门新建 部门搜索 部门的更新 部门内添加成员 导入成员
    @Test
    void testDepartAdd() {
        String departName = "开发部";
        assertTrue(mainPage.contact().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));
    }

    @AfterAll
    static void cleanup() {
//        ContactPage.clearMember("test1");
        ContactPage.clearAllDeparts("开发部");
//        WebWeworkHelperFactory.quitDriver();
    }
}
