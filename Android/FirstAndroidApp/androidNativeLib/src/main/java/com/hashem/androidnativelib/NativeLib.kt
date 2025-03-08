package com.hashem.androidnativelib

class NativeLib {

    /**
     * A native method that is implemented by the 'androidnativelib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'androidnativelib' library on application startup.
        init {
            System.loadLibrary("androidnativelib")
        }
    }
}