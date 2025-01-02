package com.example.demo.config

import com.example.demo.DemoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WebFluxConfig {

    @Bean
    fun route(
        demoHandler: DemoHandler,
    ) = coRouter {
        GET("/mongo", demoHandler::testMongo)
        GET("/redis", demoHandler::testRedis)
    }
}