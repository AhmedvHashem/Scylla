package com.hashem.firstkmpapp

class WindowsPlatform : Platform {
    override val name: String = "Windows"
}

actual fun getPlatform(): Platform = WindowsPlatform()
