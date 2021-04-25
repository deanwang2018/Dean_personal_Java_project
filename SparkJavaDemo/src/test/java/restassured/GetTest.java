package restassured;

import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * @author wangdian
 * @package restassured
 * @date 2020/12/15
 * @time 11:29
 */
public class GetTest {
    String corpid = "ww8176a55d4ad4d300";
    String corpsecret = "Yck16PcW2llmT6ha68LatpXjrGbSbjuTKFCVrHwdElU";
    String GetUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    String PostUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
    String access_token;

    @Test
    void getMethod() {
        access_token = given()
                .params("corpid", corpid, "corpsecret", corpsecret)
                .get(GetUrl)
                .then()
                .log().all()
                .extract().response().path("access_token");
        System.out.println(access_token);
    }

    @Test
    void postMethod() {
        access_token = given()
                .params("corpid", corpid, "corpsecret", corpsecret)
                .get(GetUrl)
                .then()
                .extract().response().path("access_token");
        System.out.println(access_token);

        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                        "   },\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post(PostUrl)
                .then()
                .log().all();
    }
}
