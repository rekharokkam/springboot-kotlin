package com.learning.spring.springboot.kotlin.controllers

import com.learning.spring.springboot.kotlin.livelesson.model.Joke
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class JokeControllerTest (@Autowired val client: WebTestClient) {

    @Test
    internal fun `get random joke for a default category` () {
        client.get()
                .uri("/joke")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody<Joke>()
                .consumeWith {  assertAll (
                    "Testing all the field ",
                    { assertNotNull(it.responseBody?.value) },
                    { assertNotNull(it.responseBody?.icon_url) }
                    )
                }.consumeWith { println(it.responseBody) }
    }

    @Test
    internal fun `get random joke for a passed in category` () {
        client.get()
            .uri("/joke?category=money")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Joke>()
            .consumeWith {  assertAll (
                "Testing all the field ",
                { assertNotNull(it.responseBody?.value) },
                { assertNotNull(it.responseBody?.icon_url) },
                { assertTrue(it.responseBody?.categories?.contains("money") == true) }
            )
            }.consumeWith { println(it.responseBody) }
    }
}