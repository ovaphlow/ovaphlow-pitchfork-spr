package ovaphlow.pitchfork.spr;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.entity.User;

import java.time.Duration;

@Configuration
@EnableAutoConfiguration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Document> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Document> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Document> ser = new Jackson2JsonRedisSerializer<>(Document.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration =//创建Redis的配置对象
                RedisCacheConfiguration.defaultCacheConfig()//defaultCacheConfig()方法是用来整合Redis的
                        .entryTtl(Duration.ofHours(1))//设置缓存的有效时间及自动更新策略,大家可以自行设置缓存的时间
                        .disableCachingNullValues()//禁用空值
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        // 使用GenericJackson2JsonRedisSerializer来序列化和反序列化redis的value值
        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
    }

}