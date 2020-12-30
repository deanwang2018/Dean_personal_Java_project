package web.wework.apiyaml.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.global.ApiLoader;

import java.util.ArrayList;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/26
 * @time 15:14
 */
public class Test03_ApiLoaderTest {
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoaderTest.class);

    @BeforeAll
    static void loadTest() {
        ApiLoader.load("src/test/resources/api");
        logger.info("all api loaded!");
    }

    @Test
    void getActionTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww1aa64e79931be685");
        actualParameter.add("82N9nLXm5CH7ZtY6LgkWHcVw1X4RrCSXWF8Q_R8Hzns");
        ApiLoader.getAction("tokenhelper", "getToken").run(actualParameter);
    }
}
