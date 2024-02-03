package com.learning.spring.springboot.kotlin.services

import com.learning.spring.springboot.kotlin.livelesson.json.AstroResponse
import com.learning.spring.springboot.kotlin.livelesson.services.AstroService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AstroServiceTest (@Autowired val service: AstroService){

    @Test
    internal fun `get astronauts in space through restTemplate`() {
        val astroResponse: AstroResponse = service.getAstronautsRestTemplate()
        assertEquals("success", astroResponse.message, "message is not successful")
        assertEquals(7, astroResponse.number, "number is not successful")
        assertEquals (7, astroResponse.people.size)
    }

    @Test
    internal fun `get astronauts in space through readText`() {
        val astroResponse: AstroResponse = service.getAstronautsKotlinReadText()
        assertEquals("success", astroResponse.message, "message is not successful")
        assertEquals(7, astroResponse.number, "number is not successful")
        assertEquals (7, astroResponse.people.size)
    }
}