package web.wework.apiyaml.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.global.ApiLoader;
import web.wework.framework.steps.AssertModel;
import web.wework.framework.steps.StepModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/26
 * @time 15:14
 */
public class Test04_StepModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test04_StepModelTest.class);

    @BeforeAll
    static void loadTest() {
        ApiLoader.load("src/test/resources/api");
        logger.info("all api loaded!");
    }

    @Test
    void runTest() {
//        实参
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww1aa64e79931be685");
        actualParameter.add("82N9nLXm5CH7ZtY6LgkWHcVw1X4RrCSXWF8Q_R8Hzns");

//        断言
        ArrayList<AssertModel> assertList = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01！");
        assertList.add(assertModel);

//        save
        HashMap<String, String> save = new HashMap<>();
        save.put("accesstoken","access_token");
        HashMap<String, String> globalSave = new HashMap<>();
        globalSave.put("accesstoken","access_token");

        StepModel stepModel = new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParameter);
        stepModel.setAsserts(assertList);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(globalSave);

        stepModel.run(null);
        logger.info("debugger");
    }
}
