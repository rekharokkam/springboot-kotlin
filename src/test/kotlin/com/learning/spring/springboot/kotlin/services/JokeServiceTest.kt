package com.learning.spring.springboot.kotlin.services

import com.learning.spring.springboot.kotlin.livelesson.model.Joke
import com.learning.spring.springboot.kotlin.livelesson.services.JokeService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@SpringBootTest
class JokeServiceTest (@Autowired val jokeService: JokeService) {

    val log: Logger = LoggerFactory.getLogger(JokeServiceTest::class.java)

    @Test
    fun `get random joke for a given category` () {
        val randomJoke: Joke = jokeService.getJoke("money")
        log.info("random fashion joke returned from chuck Norris : $randomJoke")
        assertNotNull(randomJoke, "random Joke for the category fashion is null")
        assertAll (
            "Validating all the fields",
            { assertNotNull(randomJoke.categories) },
            { assertNotNull(randomJoke.created_at) },
            { assertNotNull(randomJoke.icon_url) },
            { assertNotNull(randomJoke.id) },
            { assertNotNull(randomJoke.updated_at) },
            { assertNotNull(randomJoke.url) },
            { assertNotNull(randomJoke.value) },
            { assertTrue(randomJoke?.categories?.contains("fashion") == true ||
                    randomJoke?.categories?.contains("money") == true) }
        )
    }
}