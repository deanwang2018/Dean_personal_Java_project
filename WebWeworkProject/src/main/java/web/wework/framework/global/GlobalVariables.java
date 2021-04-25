package web.wework.framework.global;

import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.framework.global
 * @date 2020/12/26
 * @time 15:07
 */
public class GlobalVariables {
    static private HashMap<String, String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
