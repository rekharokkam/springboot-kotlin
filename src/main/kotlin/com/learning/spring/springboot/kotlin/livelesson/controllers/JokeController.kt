package com.learning.spring.springboot.kotlin.livelesson.controllers

import com.learning.spring.springboot.kotlin.livelesson.model.Joke
import com.learning.spring.springboot.kotlin.livelesson.services.JokeService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
@RestController
class JokeController (@Autowired val jokeService: JokeService) {

    @GetMapping ("/joke", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getJoke  (
        @RequestParam (required = false, defaultValue = "fashion") category: String) : Joke =
            jokeService.getJoke(category)

    @GetMapping ("/joke_async", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    suspend fun getJokeAsync  (
        @RequestParam (required = false, defaultValue = "fashion") category: String) : Joke =
        jokeService.getJokeAsync(category)
}

