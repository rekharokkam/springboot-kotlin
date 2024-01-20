package com.learning.spring.springboot.kotlin.controllers

import com.learning.spring.springboot.kotlin.livelesson.controllers.Greeting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GreetingTest {

    @Test
    fun `check getter and setter on Greeting` (){
        val greeting: Greeting = Greeting("Hello World")
        assertEquals ("Hello World", greeting.message, "Greeting message did not match")
    }

    @Test
    internal fun `equals is implemented correctly`() {
        val greeting1: Greeting = Greeting ("Hello World")
        val greeting2: Greeting = Greeting ("Hello World")
        assertEquals(greeting1, greeting2)
    }

    @Test
    internal fun `show the toSting implementation`() {
        val greeting: Greeting = Greeting ("Hello toString")
        println(greeting)
    }
}