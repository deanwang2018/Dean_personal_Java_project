package web.wework.po.api;

import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

/**
 * @author wangdian
 * @package web.wework.po.api
 * @date 2020/12/23
 * @time 9:44
 */
public class HelperFactory {
    String corpid = "ww1aa64e79931be685";
    String corpsecret = "82N9nLXm5CH7ZtY6LgkWHYYziKH16zltlZLX6-icmS0";
    public static final String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/";
    String access_token;
    String body;

    public String getToken() {
        access_token = get(baseUrl + "gettoken",
                "corpid", corpid,
                "corpsecret", corpsecret).path("access_token");
        return access_token;
    }

    public String setBody(String name, String name_en, int parentid, int order, int id) {
        body = "{\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"name_en\": \"" + name_en + "\",\n" +
                "   \"parentid\": " + parentid + ",\n" +
                "   \"order\": " + order + ",\n" +
                "   \"id\": " + id + "\n" +
                "}";
        return body;
    }

    public String setBody(String name, String name_en, int parentid, int order) {
        body = "{\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"name_en\": \"" + name_en + "\",\n" +
                "   \"parentid\": " + parentid + ",\n" +
                "   \"order\": " + order + "\n" +
                "}";
        return body;
    }

    public void cleanDepartmentData(String deptNamePrefix) {
        List<HashMap<String, String>> allDepart;
        Response response = get(baseUrl + "department/list", "access_token", access_token);
        allDepart = response.path("department");
        // 把name前缀为deptNamePrefix的id获取到，并删除
        allDepart.stream().forEach(dept -> {
            if (dept.get("name").contains(deptNamePrefix)) {
                System.out.println("要删：" + dept.get("name") + ", id: " + String.valueOf(dept.get("id")));
                get(baseUrl + "department/delete",
                        "access_token", access_token,
                        "id", dept.get("id"));
            }
        });
    }

    public Response createDepartment(String name, String name_en, int parentid, int order) {
        body = setBody(name, name_en, parentid, order);
        return post("application/json", body, baseUrl + "department/create?access_token=" + access_token);
    }

    public void verifyDepartment(String departmentId, String name) {
        given()
                .when()
                .get(baseUrl + "department/list?access_token=" + access_token + "&id=" + departmentId)
                .then()
                .body("department.name[0]", equalTo(name));
    }

    public Response get(String url, String parm1, Object parm2, Object... parm3) {
        return given()
                .params(parm1, parm2, parm3)
                .get(url)
                .then()
                .extract().response();
    }

    public Response post(String contentType, String body, String url) {
        return given()
                .contentType(contentType)
                .body(body)
                .when().post(url)
                .then()
                .extract()
                .response();
    }
}
