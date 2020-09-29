package example;

import org.testng.annotations.Test;

/**
 * @author wangdian
 * @package example
 * @date 2020/9/28
 * @time 14:57
 */
public class Demo_Multithreading {
    @Test(groups = {"group01"})
    public void putInA() throws Exception {
        System.out.println("装入坚果 A \n");
    }

    @Test(groups = {"group01"})
    public void putInB() throws Exception {
        System.out.println("装入坚果 B \n");
    }

    @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 60000, groups = {"group01", "group02"})
    public void putInC() throws Exception {
        Thread.sleep(1000);
        long id = Thread.currentThread().getId();
        System.out.println("线程号" + id + "装入坚果 C \n");
    }

    @Test(groups = {"group02"})
    public void putInD() throws Exception {
        System.out.println("装入坚果 D \n");
    }

    @Test(groups = {"group02"})
    public void putInE() throws Exception {
        System.out.println("装入坚果 E \n");
    }
}
