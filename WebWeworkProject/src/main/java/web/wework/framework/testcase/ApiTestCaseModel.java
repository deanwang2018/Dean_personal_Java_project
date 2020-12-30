package web.wework.framework.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.steps.StepModel;
import web.wework.framework.steps.StepResult;
import web.wework.util.FakerUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author wangdian
 * @package web.wework.framework.testcase
 * @date 2020/12/29
 * @time 11:28
 * @description 用例yaml对应的数据对象
 */
public class ApiTestCaseModel {
    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);
    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String, String> testCaseVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, String> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }

    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiTestCaseModel.class);
    }

    public void run() {
//        加载用例层关键字变量,e.g.: "部门${getTimeStamp}"
        this.testCaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新：" + testCaseVariables);

//        遍历step进行执行
        steps.forEach(step -> {
            StepResult stepResult = step.run(testCaseVariables);
            if (stepResult.getStepVariables().size() > 0) {
                testCaseVariables.putAll(stepResult.getStepVariables());
                logger.info("testcase变量更新: " + testCaseVariables);
            }
            //        处理assertList，把每步执行的assert要断言的过程保存下来
            if (stepResult.getAssertList().size() > 0) {
                assertList.addAll(stepResult.getAssertList());
            }
        });

//        统一断言
        assertAll(assertList.stream());
    }
}
