package web.wework.framework.global;

import web.wework.framework.actions.ApiActionModel;
import web.wework.framework.api.ApiObjectModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangdian
 * @package web.wework.framework.global
 * @date 2020/12/26
 * @time 15:07
 */
public class ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);
    //    加载所有api object对象并保存到本列表中
    private static List<ApiObjectModel> apis = new ArrayList<>();

    public static void load(String folder) {
        Arrays.stream(new File(folder).list()).forEach(path -> {
            try {
                apis.add(ApiObjectModel.load(folder + "/" + path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static ApiActionModel getAction(String apiName, String actionName) {
        final ApiActionModel[] apiActionModel = {new ApiActionModel()};
        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModel[0] = api.getActions().get(actionName);
        });

        if (apiActionModel[0] != null) {
            return apiActionModel[0];

        } else {
            logger.info("no api with name: " + apiName + " action: " + actionName);
        }
        return null;
    }
}
