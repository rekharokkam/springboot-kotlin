package com.learning.spring.springboot.kotlin.livelesson.services

import com.learning.spring.springboot.kotlin.livelesson.controllers.Greeting
import com.learning.spring.springboot.kotlin.livelesson.model.Joke
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.getForEntity
import org.springframework.web.client.getForObject
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.time.Duration

@Service
class JokeService (@Autowired builder: RestTemplateBuilder,
    @Autowired private val clientBuilder: WebClient.Builder) {

    private val restTemplate =
        builder.setConnectTimeout(Duration.ofSeconds(2)).build()

    companion object {
        const val BASE_URL = "https://api.chucknorris.io/jokes/random"
    }

    fun getJoke (category: String): Joke =
        restTemplate.getForObject<Joke>("$BASE_URL?category=$category")
}