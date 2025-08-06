package com.hashem.firstcmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform