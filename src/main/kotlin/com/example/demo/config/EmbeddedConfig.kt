package com.example.demo.config

import com.mongodb.reactivestreams.client.MongoClients
import com.redis.testcontainers.RedisContainer
import org.opensearch.testcontainers.OpensearchContainer
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

//@Profile("local")
@Configuration
class EmbeddedConfig {

    @Container
    @ServiceConnection
    private val redis = RedisContainer("${RedisContainer.DEFAULT_IMAGE_NAME}:${RedisContainer.DEFAULT_TAG}")
        .withExposedPorts(6379)
        .apply {
            start()
        }

    @Container
    @ServiceConnection
    private val openSearch = OpensearchContainer(DockerImageName.parse("opensearchproject/opensearch:latest"))
        .apply {
            portBindings = listOf("9200:9200")

            start()
        }

    @Container
    @ServiceConnection
    private val mongoDb = MongoDBContainer().apply {
        start()
    }

    @Bean
    fun mongoClient() = MongoClients.create(mongoDb.replicaSetUrl)
}