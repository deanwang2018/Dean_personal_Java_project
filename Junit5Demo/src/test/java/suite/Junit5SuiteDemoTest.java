package suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * @author wangdian
 * @package suite
 * @date 2020/9/29
 * @time 9:12
 */

@RunWith(JUnitPlatform.class)
@SelectPackages({"suite.com"})
@IncludePackages({"suite.com.testcase1","suite.com.testcase2"})
//@SelectClasses({suite.com.testcase.Junit5Demo1Test.class})
//@IncludeTags({"testTagDemo2"})
public class Junit5SuiteDemoTest {

}
