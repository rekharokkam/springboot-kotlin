package com.learning.spring.springboot.kotlin.controllers

import com.learning.spring.springboot.kotlin.livelesson.controllers.Greeting
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.junit.jupiter.api.Assertions.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloRestControllerWebclientTest (@Autowired val client:WebTestClient) {

    @Test
    fun `greet without name should return 'Hello World!'`() {
        client.get().uri("/rest")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Greeting>()
            .consumeWith { assertEquals("Hello World!", it.responseBody?.message) }
    }

    @Test
    fun `greet with Dolly returns 'Hello Dolly!'` () {
        client.get().uri("/rest?name=Dolly")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Greeting>()
            .consumeWith { assertEquals("Hello Dolly!", it.responseBody?.message) }
    }
}