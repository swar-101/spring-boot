package test;

import org.springframework.test.context.junit4.SpringRunner;

//@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("redisnoauth")
public class RedisCacheTest {

    @TestConfiguration
    static class RedisTestConfiguration {
    
        @Bean
        public RedisConnectionFactory redisConnectionFactory(@Value("${spring.redis.host}") String host,
                                                             @Value("${spring.redis.port}") int port) {
            return new LettuceConnectionFactory(host, port);
        }
    
        @Bean
        public RedisOperations<String, String> stringKeyAndStringValueRedisOperations(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer(UTF_8));
            redisTemplate.setValueSerializer(new StringRedisSerializer(UTF_8));
            return redisTemplate;
        }
    }

    @Test1stScenario
    public void myTest() {
      // your test
    }

}