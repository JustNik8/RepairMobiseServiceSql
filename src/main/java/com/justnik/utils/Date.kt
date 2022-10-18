package com.justnik.utils

import java.util.*

fun getRandomDate(): Date {
    val start = 31525200L
    val end = 1388520000L

    val randomTimeStamp = (start..end).random()
    return Date(randomTimeStamp)
}