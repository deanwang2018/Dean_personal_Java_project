package junit5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangdian
 * @package junit5
 * @date 2020/9/29
 * @time 11:29
 */
public class TestYaml {

    @ParameterizedTest
    @MethodSource
    public void testDDTFromYaml(User user) {
        assertTrue(user.name.length() > 3);
    }

    static List<User> testDDTFromYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        User user1 = new User();
        System.out.println(user1.toYaml());
        TypeReference typeReference = new TypeReference<List<User>>() {
        };
        List<User> users = (List<User>) mapper.readValue(
                TestYaml.class.getResourceAsStream("/user.yaml"),
                typeReference
        );
        return users;
    }
}
