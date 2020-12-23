package web.wework.api.test;

import io.restassured.response.Response;
//import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.wework.po.api.HelperFactory;

import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;

/**
 * @author wangdian
 * @package web.wework.api.test
 * @date 2020/12/23
 * @time 9:42
 */
public class DepartmentTest {
    public static String access_token;
    public static String body;
    public static HelperFactory helperFactory;
    public static String departmentId;
//    public Logger logger = Logger.getLogger(DepartmentTest.class);

    @BeforeAll
    static void beforeAll() {
        helperFactory = new HelperFactory();
        access_token = helperFactory.getToken();
        helperFactory.cleanDepartmentData("Dean");
    }

    @DisplayName("创建部门")
    @Test
    void createDepartment() {
        departmentId = helperFactory.createDepartment("Dean创建部门", "Dean_create_department", 1, 1).path("id").toString();
        helperFactory.verifyDepartment(departmentId, "Dean创建部门");
    }

    @DisplayName("更新部门")
    @Test
    void updateDepartment() {
        // 创建要更新的部门
        departmentId = helperFactory.createDepartment("Dean更新前部门", "Dean_update_department", 1, 1).path("id").toString();

        // 更新部门并验证更新后的部门名称
        body = helperFactory.setBody("Dean更新后的部门", "Dean_update_department", 1, 1, Integer.parseInt(departmentId));
        helperFactory.post("application/json", body, helperFactory.baseUrl + "department/update?access_token=" + access_token);
        helperFactory.verifyDepartment(departmentId, "Dean更新后的部门");
    }

    @DisplayName("删除部门")
    @Test
    void deleteDepartment() {
        // 创建要删除的部门
        departmentId = helperFactory.createDepartment("Dean删除部门", "Dean_delete_department", 1, 1).path("id").toString();
        helperFactory.verifyDepartment(departmentId, "Dean删除部门");

        // 删除部门
        helperFactory.get(helperFactory.baseUrl + "department/delete",
                "access_token", access_token,
                "id", Integer.parseInt(departmentId));

        // 验证list所有的部门名不包含刚删除的部门
        Response response = helperFactory.get(helperFactory.baseUrl + "department/list",
                "access_token", access_token);
        assertFalse(response.path("department.name").toString().contains("Dean删除部门"));
    }

    @DisplayName("列出部门")
    @Test
    void listDepartment() {
        // 创建要验证的部门，列出所有部门，其中包含刚创建的
        departmentId = helperFactory.createDepartment("Dean列出部门", "Dean_list_department", 1, 1).path("id").toString();
        Response response = helperFactory.get(helperFactory.baseUrl + "department/list",
                "access_token", access_token);
        assertTrue(response.path("department.name").toString().contains("Dean列出部门"));

        // 验证单独list指定的部门id
        helperFactory.verifyDepartment(departmentId, "Dean列出部门");
    }
}
