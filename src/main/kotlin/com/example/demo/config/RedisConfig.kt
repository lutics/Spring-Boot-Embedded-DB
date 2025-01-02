package com.example.demo.config

import com.redis.testcontainers.RedisContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.testcontainers.junit.jupiter.Container

//@ActiveProfiles("local")
@Configuration
class RedisConfig {

    @Container
    private val redis = RedisContainer("${RedisContainer.DEFAULT_IMAGE_NAME}:${RedisContainer.DEFAULT_TAG}")
        .withExposedPorts(6379)
        .apply {
            start()
        }

    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(redis.host, redis.getMappedPort(6379))
    }
}