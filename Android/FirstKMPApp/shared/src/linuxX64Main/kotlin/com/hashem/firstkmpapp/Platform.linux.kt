package com.hashem.firstkmpapp

class LinuxPlatform : Platform {
    override val name: String = "Linux"
}

actual fun getPlatform(): Platform = LinuxPlatform()
