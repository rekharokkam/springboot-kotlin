package com.learning.spring.springboot.kotlin.livelesson.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloRestController {

    @GetMapping (value = ["/rest"])
    fun greet (@RequestParam (required = false, defaultValue = "World") name: String): Greeting =
        Greeting("Hello $name!")
}

data class Greeting (val message: String)
