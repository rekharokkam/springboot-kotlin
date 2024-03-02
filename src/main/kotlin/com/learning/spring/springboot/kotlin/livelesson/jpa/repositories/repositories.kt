package com.learning.spring.springboot.kotlin.livelesson.jpa.repositories

import com.learning.spring.springboot.kotlin.livelesson.jpa.entities.OfficerEntity
import com.learning.spring.springboot.kotlin.livelesson.jpa.entities.Rank
import org.springframework.data.jpa.repository.JpaRepository

interface OfficerRepository: JpaRepository <OfficerEntity, Int> {

    fun findAllByLastNameLikeAndRankEquals (lastName: String, rank: Rank): OfficerEntity
}