package test_framework;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author wangdian
 * @package test_framework
 * @date 2020/11/5
 * @time 15:22
 */
public class WebTest {
    @Test
    void classic() throws IOException {
        BasePage web = UIAutoFactory.create("web");
        UIAuto uiAuto = web.load("/test_framework/webauto.yaml");
        web.run(uiAuto);
    }
}
