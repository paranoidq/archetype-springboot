package me.webapp.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataRedisTest
public class RedisTestUsage {


    @Test
    public void testRedis() throws Exception {


    }
}
