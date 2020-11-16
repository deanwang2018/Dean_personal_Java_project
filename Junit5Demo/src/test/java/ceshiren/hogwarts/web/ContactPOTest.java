package ceshiren.hogwarts.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author wangdian
 * @package ceshiren.hogwarts.web
 * @date 2020/11/14
 * @time 15:25
 */
public class ContactPOTest {
    private static MainPage mainPage;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        // 打开页面
        // 复用session登录
        mainPage = new MainPage();
        mainPage.contact().clearAllDeparts();
    }

    @Test
    void testAddMember() {
        // 跳转页面
        // 部门搜索
//        ContactPage contactPage = mainPage.contact().addMember("dean","18611111111","18611111111");
        ContactPage contactPage = mainPage.contact();
        contactPage.searchDepart("");
    }

    @Test
    void departmentSearch() throws IOException, InterruptedException {
        assertTrue(new MainPage().contact().searchDepart("测试开发部").getPartyInfo().contains("无任何成员"));
    }

    //    todo: 部门新建 部门搜索 部门的更新 部门内添加成员 导入成员
    @Test
    void testDepartAdd() {
        String departName = "";
        assertTrue(mainPage.contact().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));
    }
}
