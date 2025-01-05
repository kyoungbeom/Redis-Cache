package com.example.jediscache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.UnifiedJedis;

@Configuration
public class RedisConfig {

    @Bean
    public UnifiedJedis createJedis() {
        return new UnifiedJedis("redis://localhost:6379");
    }

}
