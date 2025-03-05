package com.hashem.firstkmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform