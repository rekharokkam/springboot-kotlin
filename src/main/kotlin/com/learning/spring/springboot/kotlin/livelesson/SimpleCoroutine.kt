package com.learning.spring.springboot.kotlin.livelesson

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextLong

suspend fun main() = coroutineScope{
    for (i in 0 until 10) {
        launch {
//            delay(1000L - (i * 10))
            delay(Random.nextLong(LongRange(50L, 100L)))
            print("$i ")
        }
    }
}