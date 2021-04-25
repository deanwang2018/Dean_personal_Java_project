package web.wework.apiyaml.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.wework.framework.actions.ApiActionModel;
import web.wework.framework.api.ApiObjectModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangdian
 * @package web.wework.apiyaml.test
 * @date 2020/12/30
 * @time 11:02
 * @description har转yaml的功能自动生成测试用例
 */
public class Test07_HarToYamlTest {
    public static final Logger logger = LoggerFactory.getLogger(Test07_HarToYamlTest.class);

    @Test
    void harTest() throws HarReaderException, IOException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));
        logger.info("debugger");

        ApiObjectModel apiObjectModel = new ApiObjectModel();
        ApiActionModel apiActionModel = new ApiActionModel();
        HashMap<String, ApiActionModel> actions = new HashMap<>();
        HashMap<String, String> queryMap = new HashMap<>();
        har.getLog().getEntries().forEach(entries -> {
            HarRequest harRequest = entries.getRequest();
            harRequest.getQueryString().forEach(query -> {
                queryMap.put(query.getName(), query.getValue());
            });

            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            apiActionModel.setQuery(queryMap);

            if (method.equals("get")) {
                apiActionModel.setGet(url);
            }

            if (method.equals("post")) {
                apiActionModel.setPost(url);
            }

            actions.put(getRequestName(url), apiActionModel);
        });

        apiObjectModel.setName("tokenhelper");
        apiObjectModel.setActions(actions);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/tokenhelper.yaml"), apiObjectModel);
    }

    public String getRequestName(String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        if (suburl.length > 1) {
            name = suburl[suburl.length - 1];
        } else if (1 == suburl.length) {
            name = suburl[0];
        }
        return name;
    }
}
