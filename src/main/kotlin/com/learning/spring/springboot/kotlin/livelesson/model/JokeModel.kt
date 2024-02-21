package com.learning.spring.springboot.kotlin.livelesson.model

data class Joke (val categories: Array<String>, val createdAt: String, val iconUrl: String,
                 val jokeId: String, val updatedAt: String, val url: String, val value: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Joke

        return categories.contentEquals(other.categories)
    }

    override fun hashCode(): Int {
        return categories.contentHashCode()
    }
}