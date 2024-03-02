package com.learning.spring.springboot.kotlin.livelesson.jpa.entities

import jakarta.persistence.*

enum class Rank {
    ENSIGN, LIEUTANANT, COMMANDER, CAPTAIN, COMMODORE, ADMIRAL
}

@Entity
@Table (name = "officers")
class OfficerEntity(
    @Enumerated (EnumType.STRING)
    val rank: Rank,
    @Column (name = "first_name")
    val firstName: String,
    @Column (name = "last_name")
    val lastName: String,
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    var id: Int? = null
)