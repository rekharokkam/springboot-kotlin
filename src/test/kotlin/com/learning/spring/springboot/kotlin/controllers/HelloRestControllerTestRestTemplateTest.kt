package com.learning.spring.springboot.kotlin.controllers

import com.learning.spring.springboot.kotlin.livelesson.controllers.Greeting
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class HelloRestControllerTestRestTemplateTest (@Autowired val template: TestRestTemplate) {

    @Test
    fun `greet without name must return 'Hello World!'` () {
        val entity = template.getForEntity<Greeting>("/rest")
        entity.headers.contentType?.let {
            assertEquals(MediaType.APPLICATION_JSON, it, "JSon is the not the format of the content type")
        } ?: fail("Missing JSON content type")
        assertEquals(HttpStatus.OK, entity.statusCode, "success status code was not returned")
        assertEquals("Hello World!", entity.body?.message)
    }

    @Test
    fun `greet with name = Dolly must return 'Hello Dolly!'` () {
        val greeting: Greeting? = template.getForObject<Greeting>("/rest?name=Dolly")
        if (greeting != null) {

        }
//        val greeting = template.getForObject("/rest?name=Dolly", Greeting::class.java)
        assertTrue( greeting?.message == "Hello Dolly!")
    }
}