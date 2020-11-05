package test_framework;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package test_framework
 * @date 2020/11/5
 * @time 13:38
 */
class FactoryTest {

    @Test
    void create() throws IOException {
        BasePage web = Factory.create("web");
        UIAuto uiAuto = web.load("/test_framework/webauto.yaml");
        web.run(uiAuto);
    }
}