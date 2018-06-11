package me.webapp;

import me.webapp.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * TODO: not working???
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
@JsonTest
public class JsonTestUsage {


    @Autowired
    private JacksonTester<User> jacksonTester;


    @Test
    public void testJsonSerialize() throws Exception {

    }


    @Test
    public void testJsonDeserialize() throws Exception {
        String json = "{\"id\":\"1\", \"username\":\"paranoidq\",\"password\":\"88863650qw\"}";
        assertThat(jacksonTester.parse(json)).isEqualTo(
            new User(1, "paranoidq", "password")
        );

    }
}
