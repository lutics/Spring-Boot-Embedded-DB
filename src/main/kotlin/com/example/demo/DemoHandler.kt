package com.example.demo

import com.example.demo.mongodb.User
import com.example.demo.mongodb.UserRepository
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import kotlin.random.Random

@Component
class DemoHandler(
    private val userRepository: UserRepository,
    private val redisTemplate: ReactiveRedisTemplate<String, String>
) {

    suspend fun testMongo(
        request: ServerRequest
    ): ServerResponse {
        val user = User(name = "Jane Doe", age = Random.nextInt(100))

        userRepository.save(user).awaitSingle()

        val result = userRepository.findAll().collectList().awaitSingle()

        return ok().bodyValueAndAwait(result)
    }

    suspend fun testRedis(
        request: ServerRequest
    ): ServerResponse {
        val key = "list"

        redisTemplate.opsForList().rightPush(key, Random.nextInt(100).toString()).awaitSingle()

        val result = redisTemplate.opsForList().range(key, 0, -1).collectList().awaitSingle()

        return ok().bodyValueAndAwait(result)
    }
}