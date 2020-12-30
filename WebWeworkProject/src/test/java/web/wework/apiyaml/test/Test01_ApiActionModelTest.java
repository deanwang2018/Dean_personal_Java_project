package web.wework.apiyaml.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.actions.ApiActionModel;
import web.wework.framework.global.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/28
 * @time 11:32
 */
public class Test01_ApiActionModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoaderTest.class);

    @Test
    void runTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww1aa64e79931be685");
        actualParameter.add("82N9nLXm5CH7ZtY6LgkWHcVw1X4RrCSXWF8Q_R8Hzns");

        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");

        HashMap<String, String> globalVariables = new HashMap<>();
        globalVariables.put("x", "gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalParam = new ArrayList<>();
        formalParam.add("corpid");
        formalParam.add("corpsecret");
        apiActionModel.setFormalParam(formalParam);

        HashMap<String, String> query = new HashMap<>();
        query.put("corpid", "${corpid}");
        query.put("corpsecret", "${corpsecret}");

        apiActionModel.setQuery(query);

        Response response = apiActionModel.run(actualParameter);
    }
}
