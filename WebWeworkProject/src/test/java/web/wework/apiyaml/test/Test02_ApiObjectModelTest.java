package web.wework.apiyaml.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.actions.ApiActionModel;
import web.wework.framework.api.ApiObjectModel;
import web.wework.framework.global.GlobalVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/28
 * @time 11:32
 */
public class Test02_ApiObjectModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoaderTest.class);

    @Test
    void loadTest() throws IOException {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww1aa64e79931be685");
        actualParameter.add("82N9nLXm5CH7ZtY6LgkWHcVw1X4RrCSXWF8Q_R8Hzns");
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParameter);
    }
}
