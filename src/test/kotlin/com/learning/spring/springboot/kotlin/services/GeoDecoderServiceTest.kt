package com.learning.spring.springboot.kotlin.services

import com.learning.spring.springboot.kotlin.livelesson.services.GeodecoderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

@SpringBootTest
internal class GeoDecoderServiceTest (@Autowired val service: GeodecoderService) {

    private val log: Logger = LoggerFactory.getLogger(GeoDecoderServiceTest::class.java )

    @Test
    fun `latitude and longitude of Boston, MA` () {
        service.getLatLon("Boston", "MA").also {
            log.info("Site for Boston, MA : $it")
        }.run {
            assertThat (this.name, notNullValue())
            assertThat(this.latitude, `is`(closeTo(42.36, 0.01)))
            assertThat(this.longitude, `is`(closeTo(71.06, 0.01)))
        }
    }

    @Test
    fun `lat lan og google headquarters` () {
        service.getLatLon("1600 Ampitheatre Parkway", "Mountain View", "CA")
            .also { log.info("Site for google headquarters : $it") }
            .run {
                assertThat(this.latitude, `is`(closeTo(37.43, 0.01)))
                assertThat(this.longitude, `is`(closeTo(-122.08, 0.02)))
            }
    }
}