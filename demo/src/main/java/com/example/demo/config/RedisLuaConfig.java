package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class RedisLuaConfig {
    @Bean
    public RedisScript<Long> deductInventoryScript() {
        Resource resource = new ClassPathResource(
                "scripts/deduct_inventory.lua");

        return RedisScript.of(
                resource,
                Long.class);
    }
}
