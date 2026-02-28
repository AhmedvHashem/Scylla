package com.hashem.shared

import platform.windows.MessageBoxA

actual fun platform(): String {
    return "Windows"
}

actual fun platform2(): String {
    val buffer = UByteArray(256)
    val size = buffer.size.toUInt()
    // A function to get the computer name, for example
    return "Windows ${buffer.toKString()}"
    return "Windows"
}

fun test() {
    MessageBoxA(null, "Hello from Kotlin/Native!", "Windows API Test", 0u)
}