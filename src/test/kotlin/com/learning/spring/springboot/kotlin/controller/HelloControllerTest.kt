package com.learning.spring.springboot.kotlin.controller

import com.learning.spring.springboot.kotlin.livelesson.controller.HelloController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.springframework.validation.support.BindingAwareModelMap

@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class HelloControllerTest {

    @Test
    fun `sayHello returns a string and adds a proper value to the model` () {
        val helloController = HelloController ()
        val model = BindingAwareModelMap ()
        val result: String = helloController.sayHello("Dolly", model)

        assertEquals(
            "hello", result,
            "data returned from the controller did not match the expectation"
        )
        assertAll(
            { assertEquals("hello", result) },
            { assertEquals("Dolly", model["user"], "model value id not match") }
        )
    }
}