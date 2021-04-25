package restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @author wangdian
 * @package restassured
 * @date 2020/12/14
 * @time 13:02
 */
public class DemoTest {

    @Test
    void fun() {
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log().all();
    }
}
