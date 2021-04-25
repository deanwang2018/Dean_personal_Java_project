package test.framework;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangdian
 * @package test.framework
 * @date 2020/11/23
 * @time 12:13
 */
public class AndroidLocator {
    public String appName;
    public String description;
    public List<HashMap<String, JSONObject>> message;
    public List<HashMap<String, JSONObject>> contact;
    public List<HashMap<String, JSONObject>> workspace;
    public List<HashMap<String, JSONObject>> myOwn;

    public String toYaml() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.writeValueAsString(this);
    }
}
