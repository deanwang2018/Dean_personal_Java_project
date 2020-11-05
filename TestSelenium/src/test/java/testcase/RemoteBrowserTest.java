package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdian
 * @package testcase
 * @date 2020/10/12
 * @time 10:33
 */
public class RemoteBrowserTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static ChromeOptions options;

    @BeforeAll
    static void initData() {
        options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        driver = new ChromeDriver(options);
//        actions = new Actions(driver);
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,3,1);

    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    void remoteBrowserTest() {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
//        driver.findElement(new By.ByCssSelector("#menu_contacts")).click();
//        driver.get(url);
//        saveCookies(driver);
//        System.out.println(driver.manage().getCookies());

        driver = new ChromeDriver();
        driver.get(url);
        addCookiesDirectly(driver);
        driver.get(url);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addCookiesDirectly(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(new Cookie("pac_uid", "0_68f64d8e335e6"));
        driver.manage().addCookie(new Cookie("iip", "0"));
        driver.manage().addCookie(new Cookie("pgv_pvid", "3562386304"));
        driver.manage().addCookie(new Cookie("Qs_lvt_323937", "1596037251"));
        driver.manage().addCookie(new Cookie("Qs_pv_323937", "1632356468884859100"));
        driver.manage().addCookie(new Cookie("pgv_pvi", "1076166656"));
        driver.manage().addCookie(new Cookie("RK", "8VaAkvYzG5"));
        driver.manage().addCookie(new Cookie("ptcz", "a46992e72dd4797a4773247da797be2df68db93e9b49f6f7b05da8a3a3cb3656"));
        driver.manage().addCookie(new Cookie("wwrtx.c_gdpr", "0"));
        driver.manage().addCookie(new Cookie("_ga", "GA1.2.240920240.1602466188"));
        driver.manage().addCookie(new Cookie("_gid", "GA1.2.1014245064.1602466188"));
        driver.manage().addCookie(new Cookie("wwrtx.ref", "direct"));
        driver.manage().addCookie(new Cookie("wwrtx.refid", "01409956"));
        driver.manage().addCookie(new Cookie("wwrtx.ltype", "1"));
        driver.manage().addCookie(new Cookie("wxpay.corpid", "1970325130162213"));
        driver.manage().addCookie(new Cookie("wxpay.vid", "1688853099234792"));
        driver.manage().addCookie(new Cookie("wwrtx.vid", "1688853099234792"));
        driver.manage().addCookie(new Cookie("Hm_lvt_9364e629af24cb52acc78b43e8c9f77d", "1602466186,1602471803,1602473013,1602478063"));
        driver.manage().addCookie(new Cookie("Hm_lpvt_9364e629af24cb52acc78b43e8c9f77d", "1602478063"));
        driver.manage().addCookie(new Cookie("wwrtx.i18n_lan", "zh"));
        driver.manage().addCookie(new Cookie("ww_rtkey", "4h4n5u4"));
        driver.manage().addCookie(new Cookie("wwrtx.d2st", "a3260767"));
        driver.manage().addCookie(new Cookie("wwrtx.sid", "3JE99s9rIMDEbFahncyv70IfmsPIySToWu2RLNLMl-76Qi40hXqgGJNV_WOVj1Ph"));
        driver.manage().addCookie(new Cookie("wwrtx.vst", "oaqgeFwgZ5RO2-Xp0zI6mF7AMbWYm_XVqQ89HNYRZatqvtOuecp0obbHECAw-RI4Pr2knaySkjf0u4GTinatC2iTU4g8XVV1dGDkLhrx1pUEfjGGTUvoKbjDNa2P6pS0SEIfNF9VEtF_V5GFgiICSwlzvu46BYGuRNfmTZty5rPXZBACIZ1tTRsAaOhGQm9HncmHrVyJvEO1kFjA8TDR8n8iVEKb9zPF5BWLdXZxusfI6EN31q9K0pnSN3GL92DwzPNuJKvTtTfNSGwaj-3M4g"));
        driver.manage().addCookie(new Cookie("_gat", "1"));
    }

    public void addCookies(WebDriver driver) {

        try {
            FileReader fileReader = new FileReader("cookie.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");

                String name = tokenizer.nextToken();
                String value = tokenizer.nextToken();
                String domain = tokenizer.nextToken();
                String path = tokenizer.nextToken();
                Date expiry = null;
                String dt = tokenizer.nextToken();
                if (dt.equals(null)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = sdf.parse(dt);
                }

                Boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());

                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);

                driver.manage().addCookie(cookie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCookies(WebDriver driver) {
        try {
            FileWriter fileWriter = new FileWriter("cookie.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write(
                        cookie.getName() + ";" +
                                cookie.getValue() + ";" +
                                cookie.getDomain() + ";" +
                                cookie.getPath() + ";" +
                                cookie.getExpiry() + ";" +
                                cookie.isSecure()
                );
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
