package junit5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author wangdian
 * @package junit5
 * @date 2020/9/29
 * @time 11:29
 */
public class User {
    public String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toYaml() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.writeValueAsString(this);
    }
}
