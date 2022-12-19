package com.hashem.mylocaleapplication

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import java.util.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setTitle(R.string.app_name)

        findViewById<View>(R.id.buttonEN).setOnClickListener {
            LocaleManager.setLanguage(this, LocaleManager.Language.ENGLISH_UK.value)
//            LocaleManager.setAppLocale(this)
//            setContentView(R.layout.activity_main2)
//            setTitle(R.string.app_name)
            recreate()
        }

        findViewById<View>(R.id.buttonAR).setOnClickListener {
            LocaleManager.setLanguage(this, LocaleManager.Language.ARABIC.value)
//            LocaleManager.setAppLocale(this)
//            setContentView(R.layout.activity_main2)
//            setTitle(R.string.app_name)
            recreate()
        }

        //device current locale
        val deviceLocale: Locale =
            ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]
        //app current locale
        val appLocale: Locale = ConfigurationCompat.getLocales(resources.configuration)[0]
        Log.d("Locale2", "[DeviceLocale=$deviceLocale] | [AppLocale=$appLocale]")

        val deviceLocale2: Locale = LocaleListCompat.getDefault()[0]
        Log.d("Locale2", "[DeviceLocale=$deviceLocale2]")
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setAppLocale(newBase))
    }
}