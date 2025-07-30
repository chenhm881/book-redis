package com.heiio.bookredis.config;

import com.heiio.bookredis.model.Article;
import com.heiio.bookredis.model.blog.Blog;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory connectionFactory){
//        RedisTemplate<Object,Object> redisTemplate= new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 使用GenericJackson2JsonRedisSerializer序列化值（支持任意对象）
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        // 键仍可使用StringRedisSerializer（但需确保键能转为String，或也用Generic序列化器）
        GenericJackson2JsonRedisSerializer keySerializer = new GenericJackson2JsonRedisSerializer();

        // 设置键序列化器
        template.setKeySerializer(keySerializer);
        // 设置值序列化器
        template.setValueSerializer(valueSerializer);

        // 可选：设置哈希键和哈希值的序列化器
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }

//    @Primary
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        //初始化一个RedisCacheWriter
//        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//        //设置CacheManager的值序列化方式为json序列化
//        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
//        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
//                .fromSerializer(jsonSerializer);
//
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(pair);
//        RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(RedisSerializationContext.SerializationPair
//                .fromSerializer(new StringRedisSerializer()));
//        //设置默认超过期时间是30秒
//        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
//        //初始化RedisCacheManager
//        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
//    }

//
//    @Bean
//    @Primary
//    public CacheManager articleCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        //初始化一个RedisCacheWriter
//        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//        //设置CacheManager的值序列化方式为json序列化
//        RedisSerializer jsonSerializer = new JdkSerializationRedisSerializer();
//        RedisSerializationContext.SerializationPair<Article> pair = RedisSerializationContext.SerializationPair
//                .fromSerializer(jsonSerializer);
//
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(pair);
//        RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(RedisSerializationContext.SerializationPair
//                .fromSerializer(new StringRedisSerializer()));
//        //设置默认超过期时间是30秒
//        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
//        //初始化RedisCacheManager
//        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
//    }
//
    @Bean
    public CacheManager blogCacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Blog> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer()));
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }


}
