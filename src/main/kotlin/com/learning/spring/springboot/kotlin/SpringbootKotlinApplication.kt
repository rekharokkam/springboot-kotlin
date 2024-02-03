package com.learning.spring.springboot.kotlin

import com.learning.spring.springboot.kotlin.livelesson.services.myPrintln
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootKotlinApplication

fun main(args: Array<String>) {
	myPrintln("from the main method")
	runApplication<SpringbootKotlinApplication>(*args)
}
