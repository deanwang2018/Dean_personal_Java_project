package web.wework.apiyaml.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.global.ApiLoader;
import web.wework.framework.testcase.ApiTestCaseModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/30
 * @time 10:32
 * @description junit5参数化用例测试
 */
public class Test06_ApiParameterizedTest {
    public static final Logger logger = LoggerFactory.getLogger(Test06_ApiParameterizedTest.class);
    @ParameterizedTest(name = "{index} {2}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel, String name, String description) {
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        apiTestCaseModel.run();
    }

    static List<Arguments> apiTest() {

        ApiLoader.load("src/test/resources/api");

        //用来传递给参数化用例
        List<Arguments> testcases = new ArrayList<>();

        //读取所有的测试用例
        String testcaseDir = "src/test/resources/testcase";
        /**
         * 1、遍历目录下所有的用例文件，并组装成参数列表
         */
        Arrays.stream(new File(testcaseDir).list())
                .forEach(name -> {
                    String path = testcaseDir + "/" + name;
                    try {
                        ApiTestCaseModel apiTestCase = ApiTestCaseModel.load(path);
                        testcases.add(arguments(apiTestCase, apiTestCase.getName(), apiTestCase.getDescription()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return testcases;
    }
}
