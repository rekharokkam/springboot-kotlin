package com.learning.spring.springboot.kotlin.controllers

import com.learning.spring.springboot.kotlin.livelesson.controllers.HelloController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest (HelloController::class)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class HelloControllerMVCTest (@Autowired val mvc: MockMvc) {

    @Test
    internal fun `check sayHello without name` () {
        mvc.get("/hello") {
            accept = MediaType.TEXT_PLAIN
        }.andExpect {
            status { isOk() }
            view { name("hello") }
            model { attribute("user", "World")  }
        }
    }

    @Test
    internal fun `check sayHello with name` () {
        mvc.get("/hello") {
            accept = MediaType.TEXT_PLAIN
            param("name", "Dolly")
        }.andExpect {
            status { isOk() }
            view { name("hello") }
            model { attribute("user", "Dolly") }
        }
    }
}