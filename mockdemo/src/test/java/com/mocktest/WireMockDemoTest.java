package com.mocktest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * @author wangdian
 * @package com
 * @date 2020/12/10
 * @time 12:50
 */
public class WireMockDemoTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void BeforeAll() {
        //No-args constructor will start on port 8080, no HTTPS
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        configureFor("localhost", 8089);
    }

    @Test
    void stubMock() {

        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("Hello World!")));

        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void easy_Mock() {
        String content = "Java名企业定向班4期";
        try {
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>Some content</response>")));
            Thread.sleep(10000);
            reset();
            stubFor(get(urlEqualTo("/my/resource"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody(content)));

            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void proxyMockTest() {
        String path = WireMockDemoTest.class.getResource("/mock.json").getPath().substring(1);
        System.out.println("路径："+path);
        try {
            // Low priority catch-all proxies to otherhost.com by default
            stubFor(get(urlMatching(".*")).atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

            // High priority stub will send a Service Unavailable response
// if the specified URL is requested
            stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(1)
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(path)))));

            Thread.sleep(5000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
