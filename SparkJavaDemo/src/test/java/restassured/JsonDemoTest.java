package restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author wangdian
 * @package restassured
 * @date 2020/12/17
 * @time 8:57
 */
public class JsonDemoTest {

    @Test
    void testJson() {
//        given()
//                .when()
//                .get("http://localhost:8000/lotto.json")
//                .then().body("lotto.lottoId", equalTo(5))
//                .body("lotto.winners.winnerId", hasItems(54, 23));

        given()
                .when()
                .get("http://localhost:8000/books.json")
                .then().body("store.book.category", hasItems("reference"))
                .body("store.book[0].author", equalTo("Nigel Rees"))
                .body("store.book.findAll{book -> book.price >=5 && book.price <=15 }.title", hasItems("Moby Dick"));
    }

    @Test
    void testXML() {
        given().when().get("http://localhost:8000/shopping.xml")
                .then()
//                .body("shopping.category[0].item[0]", equalTo("Chocolate"))
//                .body("shopping.category.item.size()", equalTo(5))
//                .body("shopping.category.findAll { it.@type=='groceries' }.item.size()", equalTo(2))
//                .body("hopping.category.findAll { it.@type=='groceries' }.item.size()", equalTo(2))
                .body("**.findAll { it.@type=='groceries' }.item[0]", equalTo("Chocolate"));
    }

    @Test
    void xueqiuLoginTest() {
        String code = given()
                .header("User-Agent", "Xueqiu Android 12.1.1")
                .queryParam("_t", "1UNKNOWN8f5ef25fe7030155dccb5a28097e74d7.9588493382.1608187104504.1608187303338")
                .queryParam("_s", "978002")
                .cookie("u", "9588493382")
                .cookie("xq_a_token", "71bd7978b29b7f64bb6481d90c99313f11de1dcb")
                .formParam("grant_type", "password")
                .formParam("telephone", "18610011672")
                .formParam("password", "e10adc3949ba59abbe56e057f20f883e")
                .formParam("areacode", "86")
                .formParam("captcha", "")
                .formParam("client_id", "JtXbaMn7eP")
                .formParam("client_secret", "txsDfr9FphRSPov5oQou74")
                .formParam("sid", "1UNKNOWN8f5ef25fe7030155dccb5a28097e74d7")
                .when().post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .log().all()
                .statusCode(400)
                .body("error_code", equalTo("20082"))
                .extract().path("error_code");

        System.out.println(code);
    }

    @Test
    void xueqiuLoginTest2() {
//        RestAssured.proxy("127.0.0.1:7778");
        Response response = given()
                .header("User-Agent", "Xueqiu Android 12.1.1")
                .queryParam("_t", "1UNKNOWN8f5ef25fe7030155dccb5a28097e74d7.9588493382.1608187104504.1608187303338")
                .queryParam("_s", "978002")
                .cookie("u", "9588493382")
                .cookie("xq_a_token", "71bd7978b29b7f64bb6481d90c99313f11de1dcb")
                .formParam("grant_type", "password")
                .formParam("telephone", "18610011672")
                .formParam("password", "e10adc3949ba59abbe56e057f20f883e")
                .formParam("areacode", "86")
                .formParam("captcha", "")
                .formParam("client_id", "JtXbaMn7eP")
                .formParam("client_secret", "txsDfr9FphRSPov5oQou74")
                .formParam("sid", "1UNKNOWN8f5ef25fe7030155dccb5a28097e74d7")
                .when().post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .log().all()
                .statusCode(400)
                .body("error_code", equalTo("20082"))
                .extract().response();

        System.out.println(response);
    }

    @Test
    void testPostJson(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("a",1);
        map.put("b",2);
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when().post("https://www.baidu.com/")
                .then().statusCode(200);
    }
}
