package web.wework.framework.steps;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.global.ApiLoader;
import web.wework.framework.global.GlobalVariables;
import web.wework.util.PlaceholderUtils;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author wangdian
 * @package web.wework.framework.steps
 * @date 2020/12/26
 * @time 15:36
 */
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    //    1. 需要定义AssertModel类
    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String, String> save;
    private HashMap<String, String> saveGlobal;

    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private HashMap<String, String> stepVariables = new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public StepResult run(HashMap<String, String> testCaseVariables) {
//        需要定义AssertModel类
        if (actualParameter != null) {
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter, testCaseVariables));
        }

//        根据case中配置的API对象和action信息，取出并执行相应的action
        Response response = ApiLoader.getAction(api, action).run(finalActualParameter);

//        存储save
        if (save != null) {
            save.forEach((variableName, path) -> {
                String value = response.path(path).toString();
                stepVariables.put(variableName, value);
                logger.info("局部变量更新：" + stepVariables);
            });
        }

//        存储globalSave
        if (saveGlobal != null) {
            saveGlobal.forEach((variableName, path) -> {
                String value = response.path(path).toString();
                GlobalVariables.getGlobalVariables().put(variableName, value);
                logger.info("全局变量更新：" + GlobalVariables.getGlobalVariables());
            });
        }

//        处理软断言需要的中间断言数据
        if (asserts != null) {
            asserts.stream().forEach(assertModel -> {
//                获取断言方法，如果没有断言方法默认使用equalTo
                Matcher matcher = null;
                if (assertModel.getMatcher() != null) {
                    logger.info("获取断言方法" + assertModel.getMatcher() + "返回Matcher类型");
                    try {
                        matcher = matcherMethod(assertModel.getMatcher(), assertModel.getExpect());
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    matcher = equalTo(assertModel.getExpect());
                }
                Matcher finalMatcher = matcher;
                assertList.add(() -> {
                    assertThat(assertModel.getReason(),
                            response.path(assertModel.getActual()).toString(),
                            finalMatcher);
                });
            });
        }

//        将response和断言结果存储到stepResult对象中并返回
        stepResult.setAssertList(assertList);
//        把save的step里的变量追加到testcase变量里一并传出去
        testCaseVariables.putAll(stepVariables);
        logger.info("testcase变量更新：" + testCaseVariables);
        stepResult.setStepVariables(testCaseVariables);
        stepResult.setResponse(response);

        return stepResult;
    }

    //反射找Matchers静态方法
    static Matcher matcherMethod(String methodValue, String expectValue) throws
            InvocationTargetException, IllegalAccessException {
        Method method = Arrays.stream(org.hamcrest.Matchers.class.getDeclaredMethods())
                .filter(m -> m.getName().equals(methodValue))
                .findFirst().get();
        return (Matcher) method.invoke(null, expectValue);
    }

}
