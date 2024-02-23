package com.learning.spring.springboot.kotlin.livelesson.services

import com.learning.spring.springboot.kotlin.livelesson.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.getForObject
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.time.Duration

@Service
class JokeService (@Autowired builder: RestTemplateBuilder,
    @Autowired private val clientBuilder: WebClient.Builder) {

    companion object {
        const val BASE_URL = "https://api.chucknorris.io/jokes/random"
    }

    private val restTemplate =
        builder.setConnectTimeout(Duration.ofSeconds(2)).build()

    private val webClient = clientBuilder.baseUrl(BASE_URL).build()

    fun getJoke (category: String): Joke =
        restTemplate.getForObject<Joke>("$BASE_URL?category=$category")

    suspend fun getJokeAsync (category: String): Joke =
        withContext(Dispatchers.IO) {
            webClient.get()
                .uri("$BASE_URL?category=$category")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<Joke>()
        }
}