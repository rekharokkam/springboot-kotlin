package com.learning.spring.springboot.kotlin.livelesson.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HelloController {

    @GetMapping (value = ["/hello"])
    fun sayHello (@RequestParam(required = false, defaultValue = "World") name: String?, model: Model): String {
        println("Inside the sayHello method of MVC Controller")
        if (null != name) {
            model["user"] = name
        }
        return "hello"
    }
}