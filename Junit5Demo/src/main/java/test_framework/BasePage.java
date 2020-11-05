package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangdian
 * @package test_framework
 * @date 2020/10/29
 * @time 14:23
 */
public class BasePage {
    public void click(HashMap<String, Object> map) {
        System.out.println("click");
        System.out.println(map);
    }

    public void sendKeys(HashMap<String, Object> map) {
        System.out.println("sendKeys");
        System.out.println(map);
    }

    public void action(HashMap<String, Object> map) {
        System.out.println("action");
        System.out.println(map);
    }

    public void find() {

    }

    public void getText() {

    }

    public void run(UIAuto uiAuto) {
        uiAuto.steps.stream().forEach(m -> {
//            if (m.keySet().contains("click")) {
//                click((HashMap<String, Object>) m.get("click"));
//            }
            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }

            if (m.containsKey("action")) {
                action(m);
            }
        });
    }

    public UIAuto load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiauto = mapper.readValue(BasePage.class.getResourceAsStream(path), UIAuto.class);
        return uiauto;
    }
}
