package web.wework.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

/**
 * @author wangdian
 * @package web.wework.framework
 * @date 2020/12/29
 * @time 10:40
 * @description 用于存储response等返回的类
 */
public class BaseResult {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static Object load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), Object.class);
    }
}
