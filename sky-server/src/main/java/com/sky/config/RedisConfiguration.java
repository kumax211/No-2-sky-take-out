package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @projectName: sky-take-out
 * @package: com.sky.config
 * @className: RedisConfigration
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/30 19:18
 * @version: 1.0
 */
@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("开始创建redis 模版对象");
        // 创建redis模版对象
        RedisTemplate redisTemplate = new RedisTemplate();
       // 设置redis模版对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
       // 设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
