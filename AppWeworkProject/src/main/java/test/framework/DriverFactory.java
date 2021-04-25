package test.framework;

/**
 * @author wangdian
 * @package test.framework
 * @date 2020/11/23
 * @time 12:12
 */
public class DriverFactory {
    public static DriverPage create(String driverName){
        if(driverName.equals("web_chrome")){
            return new web.wework.pageobject.BasePage("chrome");
        }

        if(driverName.equals("web_firefox")){
            return new web.wework.pageobject.BasePage("firefox");
        }

        if(driverName.equals("web_edge")){
            return new web.wework.pageobject.BasePage("edge");
        }

        if(driverName.equals("app")){
            return new app.wework.pageobject.BasePage();
        }

        if(driverName.equals("uiautomator")){
//            return new AppBasePage();
        }

        if(driverName.equals("atx")){
//            return new AppBasePage();
        }

        if(driverName.equals("macaca")){
//            return new AppBasePage();
        }

        return null;
    }
}
