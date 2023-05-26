package com.hashem.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Hashem.
 */

class MySingleton private constructor(callback: () -> Unit) {
    companion object {
        private var ourInstance: MySingleton? = null

        fun getInstance(callback: () -> Unit): MySingleton? {
            if (ourInstance == null) {
                ourInstance = MySingleton(callback)
            }
            return ourInstance
        }
    }

    init {
        CoroutineScope(Main).launch {
            delay(5000)
            callback()
        }
    }
}