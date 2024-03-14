package com.learning.spring.springboot.kotlin.jpa.repository

import com.learning.spring.springboot.kotlin.livelesson.jpa.entities.OfficerEntity
import com.learning.spring.springboot.kotlin.livelesson.jpa.entities.Rank
import com.learning.spring.springboot.kotlin.livelesson.jpa.repositories.OfficerRepository
import jakarta.transaction.Transactional
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.junit.jupiter.api.Assertions.*
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.`is`


@SpringBootTest
@Transactional
internal class OfficerRepositoryTest (@Autowired val officerRepository: OfficerRepository,
    @Autowired private val template: JdbcTemplate) {

    private fun getIds() = template.query("select id from officers")
        {rs, _ -> rs.getInt("id")}

    @Test
    internal fun `save should return officer with non-null id` () {
//        val officer: OfficerEntity = OfficerEntity (rank = Rank.ENSIGN, firstName = "Josh",
//            lastName = "Bai")
//        val officerSaved: OfficerEntity = officerRepository.save(officer)
//        assertNotNull(officerSaved.id)

        OfficerEntity (rank = Rank.ENSIGN, firstName = "Josh",
            lastName = "Bai")
            .apply { officerRepository.save(this) }
            .also { println("Newly created Officer Id : ${it.id}") }
            .also { assertNotNull(it.id) }
    }

    @Test
    internal fun `officer with given lastName and rank exists` () {
        officerRepository.findAllByLastNameLikeAndRankEquals("Kirk", Rank.CAPTAIN)
            .also { println("${it.id}, ${it.firstName}, ${it.lastName}, ${it.rank}") }
            .also { assertNotNull(it.id)
                assertEquals("James", it.firstName)
            }
    }

    @Test
    internal fun `officer with id 1 exists` () {
        officerRepository.findById(1)
            .also {
                assertTrue(it.isPresent)
                assert(it.get().id == 1)
            }
            .also { println("officer details: ${it.get().id}, ${it.get().firstName}, " +
                    "${it.get().lastName}, ${it.get().rank}") }
    }

    @Test
    internal fun `findById works for all Ids in the table` () {
        getIds().forEach {
            eachId -> officerRepository.findById(eachId)
                .also {
                    assertTrue(it.isPresent)
                    assertEquals(eachId, it.get().id)
                }.also {
                    assert(officerRepository.existsById(eachId))
            }
        }
    }

    @Test
    fun `delete all officers` () {
        getIds().forEach {
            eachId ->
                val officerEntityOpt = officerRepository.findById(eachId)
                assert(officerEntityOpt.isPresent)
//                officerRepository.deleteById(eachId)
            officerRepository.delete(officerEntityOpt.get())
        }
        assertThat(officerRepository.count(), `is`(0L))
    }

    @Test
    fun `should be 5 officers in test db` () {
//        assertTrue(officerRepository.count() == 5L)
        assertEquals(5L, officerRepository.count())
    }

    @Test
    fun `check officers last names in test db` () {
//        val lastNames = officerRepository.findAll().map { it -> it.lastName }
//        assertThat(lastNames,
//            containsInAnyOrder("Janeway", "Archer", "Kirk", "Picard", "Sisko"))

        officerRepository.findAll().map { it.lastName }
            .also { lastNames ->
                assertThat(lastNames, containsInAnyOrder("Janeway", "Archer", "Kirk", "Picard", "Sisko"))
            }
            .also {
                assertThat(it, containsInAnyOrder("Janeway", "Archer", "Kirk", "Picard", "Sisko"))
            }
    }

    @Test
    fun `Officer with id 999 does not exist` () {
        assertFalse(officerRepository.findById(999).isPresent)
    }
}