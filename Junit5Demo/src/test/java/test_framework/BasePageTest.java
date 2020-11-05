package test_framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package test_framework
 * @date 2020/10/29
 * @time 14:26
 */
class BasePageTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll() {
        basePage = new BasePage();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() throws IOException {
        UIAuto uiauto = basePage.load("/test_framework/uiauto.yaml");
        basePage.run(uiauto);
    }

    @Test
    void load() throws IOException {
        UIAuto uiauto = basePage.load("/test_framework/uiauto.yaml");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));

    }
}