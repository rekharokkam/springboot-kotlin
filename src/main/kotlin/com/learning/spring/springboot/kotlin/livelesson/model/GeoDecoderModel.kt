package com.learning.spring.springboot.kotlin.livelesson.model

data class Location (val lat: Double, val lng: Double)

data class Geometry (val location: Location)

data class Result (val formattedAddress: String, val geometry: Geometry)

data class Response (val status: String, val results: List<Result>) {
    val location: Location
        get() = results[0].geometry.location

    val formattedAddress: String
        get() = results[0].formattedAddress
}

data class Site (val name: String, val latitude: Double, val longitude: Double)