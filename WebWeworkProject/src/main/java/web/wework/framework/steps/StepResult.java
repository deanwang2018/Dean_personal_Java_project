package web.wework.framework.steps;

import org.junit.jupiter.api.function.Executable;
import web.wework.framework.BaseResult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.framework.steps
 * @date 2020/12/26
 * @time 15:49
 */
public class StepResult extends BaseResult {
    private ArrayList<Executable> assertList;
    private HashMap<String,String> stepVariables = new HashMap<>();

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }


}
