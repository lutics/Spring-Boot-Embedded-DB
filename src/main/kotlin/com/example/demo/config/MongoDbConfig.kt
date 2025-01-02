package com.example.demo.config

import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container

//@ActiveProfiles("local")
@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.example.demo.mongodb"])
class MongoDbConfig {

    @Container
    private val mongoDb = MongoDBContainer().apply {
        start()
    }

    @Bean
    fun mongoClient() = MongoClients.create(mongoDb.replicaSetUrl)
}