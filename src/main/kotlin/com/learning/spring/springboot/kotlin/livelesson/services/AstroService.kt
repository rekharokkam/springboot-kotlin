package com.learning.spring.springboot.kotlin.livelesson.services

import com.google.gson.Gson
import com.learning.spring.springboot.kotlin.livelesson.model.AstroResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.net.URL

import java.time.Duration

@Service
class AstroService (@Autowired val builder: RestTemplateBuilder) {

    private val restTemplate: RestTemplate =
        builder.setConnectTimeout(Duration.ofSeconds(2)).build()

    companion object {
        const val BASE_URL = "http://api.open-notify.org/astros.json"
    }

    fun getAstronautsRestTemplate (): AstroResponse {
        println("Inside the getAstronautsRestTemplate method inside the AstroService")
        return restTemplate.getForObject<AstroResponse>(BASE_URL)
    }

    fun getAstronautsKotlinReadText (): AstroResponse =
         Gson().fromJson<AstroResponse>(
            URL(BASE_URL).readText(), AstroResponse::class.java)
}