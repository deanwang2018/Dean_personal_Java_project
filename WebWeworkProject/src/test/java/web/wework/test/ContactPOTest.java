package web.wework.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import web.wework.src.ContactPage;
import web.wework.src.MainPage;
import web.wework.src.WebWeworkHelperFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package web.wework.test
 * @date 2020/11/14
 * @time 15:25
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactPOTest extends WebWeworkHelperFactory {
    private static MainPage mainPage;
    //    参数化的变量
    public static String user;
    public static String accid;
    public static String mobile;
    public static String deptSearch;
    public static String noMember;
    public static String oldDept;
    public static String newDept;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        // 打开页面, 复用session登录
        mainPage = new MainPage();
        setValue();
//        mainPage.contact().clearAllDeparts();
    }

    public static void setValue() throws IOException {
        ContactTestParam contactTestParam = loadTest("/ContactTestParm.yaml");
        contactTestParam.steps.stream().forEach(m -> {
                    if (m.containsKey("user")) {
                        user = (String) m.get("user");
                    }

                    if (m.containsKey("accid")) {
                        accid = (String) m.get("accid");
                    }

                    if (m.containsKey("mobile")) {
                        mobile = (String) m.get("mobile");
                    }

                    if (m.containsKey("deptSearch")) {
                        deptSearch = (String) m.get("deptSearch");
                    }

                    if (m.containsKey("noMember")) {
                        noMember = (String) m.get("noMember");
                    }

                    if (m.containsKey("oldDept")) {
                        oldDept = (String) m.get("oldDept");
                    }

                    if (m.containsKey("noDept")) {
                        newDept = (String) m.get("newDept");
                    }
                }

        );
    }

    public static ContactTestParam loadTest(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ContactTestParam contactTestParam = mapper.readValue(ContactPOTest.class.getResourceAsStream(path), ContactTestParam.class);
        return contactTestParam;
    }

    //    添加成员
    @Order(1)
//    @ParameterizedTest
//    @MethodSource("ContactTestParmList")
    @Test
    void testMainPageAddMember() throws InterruptedException {
        mainPage.AddMember(user, accid, mobile);
        assertTrue(mainPage.contact().searchMemberContext(user).contains(user));
        mainPage.contact().deleteMember(user);
    }

    //    搜索无成员的部门
    @Order(2)
//    @ParameterizedTest
//    @MethodSource("ContactTestParmList")
    @Test
    void departmentSearch() throws IOException, InterruptedException {
        assertTrue(new MainPage().contact().searchDepart(deptSearch).getPartyInfo().contains(noMember));
    }

    //   部门新建 部门搜索
    @Order(3)
//    @ParameterizedTest
//    @MethodSource("ContactTestParmList")
    @Test
    void testDepartAdd() throws InterruptedException {
        assertTrue(mainPage.contact().
                addDepart(oldDept).searchDepart(oldDept).getPartyInfo().contains(oldDept));
    }

    //    部门名称更新
    @Order(4)
//    @ParameterizedTest
//    @MethodSource("ContactTestParmList")
    @Test
    void testDepartNameUpdate() {
        assertTrue(mainPage.contact().
                updateDepartName(oldDept, newDept).
                searchDepart(newDept).getPartyInfo().contains(newDept));
    }

    //    部门内添加成员 删除成员
    @Order(5)
//    @ParameterizedTest
//    @MethodSource("ContactTestParmList")
    @Test
    void testDepartAddDeleteMember() throws InterruptedException {
        mainPage.contact().searchDepart(newDept).
                addMemberInDepart(newDept, user, accid, mobile);
        assertTrue(mainPage.contact().deleteMember(user).
                searchDepart(newDept).getPartyInfo().contains(noMember));
    }

//    @AfterAll
//    static void cleanup() throws InterruptedException {
//        ContactPage.clearMember();
//        ContactPage.clearAllDeparts("质量保障部");
//        WebWeworkHelperFactory.quitDriver();
//    }
}
