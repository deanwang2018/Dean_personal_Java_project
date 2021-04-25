package web.wework.apiyaml.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.global.ApiLoader;
import web.wework.framework.testcase.ApiTestCaseModel;

import java.io.IOException;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/26
 * @time 15:14
 */
public class Test05_ApiTestCaseModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_ApiTestCaseModelTest.class);

    @BeforeAll
    static void loadTest() {
        ApiLoader.load("src/test/resources/api");
        logger.info("all api loaded!");
    }

    @Test
    void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcase/testcase_department.yaml");
        apiTestCaseModel.run();
        logger.info("debugger");
    }
}
