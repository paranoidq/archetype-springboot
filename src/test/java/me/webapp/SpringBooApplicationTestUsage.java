package me.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * SpringBoot应用测试举例
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
public class SpringBooApplicationTestUsage {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * 自动注入{@link WebTestClient}实例
     * 需要添加注解{@link AutoConfigureWebClient}，并添加spring-boot-starter-webflux依赖
     */
    @Autowired
    private WebTestClient webTestClient;


    /**
     * 通过{@link TestRestTemplate}测试API接口
     */
	@Test
	public void testWithRestTemplate() {
        testRestTemplate.getForObject("/hello", String.class).equals("123");
    }


    /**
     * 通过{@link WebTestClient}测试API接口
     */
    @Test
    public void testWithWebTestClient() {
        webTestClient.get().uri("/boot/hello").exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("123");
    }




}
