package com.learning.spring.springboot.kotlin.livelesson.model

data class Astronaut (val name: String, val craft: String)

data class AstroResponse (val message: String, val number: Int, val people: List<Astronaut>)

