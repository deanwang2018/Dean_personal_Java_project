package example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.Calculator;
import util.FakerUtils;

import java.io.IOException;

/**
 * @author wangdian
 * @package example
 * @date 2020/9/28
 * @time 15:06
 */
public class Demo_DataProvider {
    @DataProvider(name = "putInList")
    public Object[][] putInList() {
        Object[][] object;
        return object = new Object[][]{
                {"包裹1", 1, 0, 1, 1, 1},
                {"包裹2", 0, 1, 1, 1, 0},
                {"包裹3", 1, 1, 1, 1, 1}
        };
    }

    @Test(dataProvider = "putInList")
    public void putInA(String packageName, int aNum, int bNum, int cNum, int dNum, int eNum) throws Exception {
        System.out.println("忧伤哥 操作" + packageName + "装入坚果A " + aNum + "个！ \n");
        Thread.sleep(1000);
        System.out.println("炫酷哥 操作" + packageName + "装入坚果B " + bNum + "个！ \n");
        Thread.sleep(1000);
        System.out.println("羞涩哥 操作" + packageName + "装入坚果C " + cNum + "个！ \n");
        Thread.sleep(1000);
        System.out.println("暴怒哥 操作" + packageName + "装入坚果D " + dNum + "个！ \n");
        Thread.sleep(1000);
        System.out.println("傻笑哥 操作" + packageName + "装入坚果E " + eNum + "个！ \n");
        Thread.sleep(1000);
    }

    @DataProvider(name = "testData")
    public static Object[][] words() throws IOException {
        return FakerUtils.getTestData("/src/main/resources/data/divideparam.csv");
    }

    @Test(threadPoolSize = 1, invocationCount = 1, dataProvider = "testData")
    public void divTest(String x, String y) throws InterruptedException {
        int result = Calculator.divide(Integer.valueOf(x), Integer.valueOf(y));
        System.out.println(result);
    }

    @DataProvider(name = "testData2")
    public static Object[][] words2() throws IOException {
        return FakerUtils.getTestData("/src/main/resources/data/multicolumn.csv");
    }

    @Test(dataProvider = "testData2")
    public void multiColumnTest(String x, String y, String a, String b, String c, String d) throws InterruptedException {
        System.out.println("x:" + x + ",y:" + y + ",a:" + a + ",b:" + b + ",c:" + c + ",d:" + d);
    }
}
