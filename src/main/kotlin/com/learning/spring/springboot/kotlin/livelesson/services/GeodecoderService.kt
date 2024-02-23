package com.learning.spring.springboot.kotlin.livelesson.services

import com.learning.spring.springboot.kotlin.livelesson.model.Response
import com.learning.spring.springboot.kotlin.livelesson.model.Site
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.getForObject
import java.net.URLEncoder
import java.time.Duration

@Service
class GeodecoderService (@Autowired builder: RestTemplateBuilder) {

    private val restTemplate =
        builder.setConnectTimeout(Duration.ofSeconds(2)).build()

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json"
        const val API_KEY = ""
    }

    fun getLatLon (vararg address: String): Site {
        val encodedStr = address.joinToString(",")
            { URLEncoder.encode(it, "UTF-8") }
        val url = "$BASE_URL?address=$encodedStr&key=$API_KEY"
        println("url : $url")
        val site: Site = restTemplate.getForObject<Response> (url).let {
            Site (name = it.formattedAddress,
                latitude = it.location.lat,
                longitude = it.location.lng)
        }.also { println(it) }
        return site
    }
}