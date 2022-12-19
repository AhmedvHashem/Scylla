package com.hashem.mylocaleapplication

import android.app.Application
import android.content.Context

/**
 * Created by Hashem.
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setAppLocale(newBase))
    }
}