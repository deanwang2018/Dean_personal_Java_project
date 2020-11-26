package app.wework.test;

import app.wework.pageobject.MainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author wangdian
 * @package app.wework.test
 * @date 2020/11/21
 * @time 16:46
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartPOTest {
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
    @ParameterizedTest
    @CsvSource({
            "科技12部, 性能测试部",
            "UI测试部, UI压力测试部"})
    void addDepart(String highLevelDept, String departName) throws IOException, InterruptedException {
        assertTrue(mainPage.contact().addDepart(highLevelDept, departName).isSearchExist(departName));
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({
            "性能测试部, API测试部",
            "UI压力测试部, UI测试第二组"})
    void updateDepartName(String oldDept, String newDept) throws IOException, InterruptedException {
        assertTrue(mainPage.contact().updateDepartName(oldDept, newDept).isSearchExist(newDept));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"API测试部","UI测试第二组"})
    void deleteDepart(String deptName) throws IOException, InterruptedException {
        assertTrue(!mainPage.contact().deleteDepartName(deptName).isSearchExist(deptName));
    }
}
