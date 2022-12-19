package com.hashem.mylocaleapplication

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)

        findViewById<View>(R.id.btn).setOnClickListener {
            startActivity(Intent(application, MainActivity2::class.java))
        }

        //device current locale
        val deviceLocale: Locale =
            ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]
        //app current locale
        val appLocale: Locale = ConfigurationCompat.getLocales(resources.configuration)[0]
        Log.d("Locale", "[DeviceLocale=$deviceLocale] | [AppLocale=$appLocale]")

        val deviceLocale2: Locale = LocaleListCompat.getDefault()[0]
        Log.d("Locale", "[DeviceLocale=$deviceLocale2]")
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setAppLocale(newBase))
    }
}