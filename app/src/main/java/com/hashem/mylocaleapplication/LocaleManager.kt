package com.hashem.mylocaleapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION_CODES.N
import android.os.Build.VERSION_CODES.N_MR1
import android.os.LocaleList
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.Fragment
import java.util.*


/**
 * Created by shar2awy on 2019-09-30.
 */
@Suppress("DEPRECATION")
@SuppressLint("ApplySharedPref")
object LocaleManager {

    enum class Language(var value: String) {
        ENGLISH_US("en_US"),
        ENGLISH_UK("en_GB"),
        ARABIC("ar")
    }

    private const val LOCAL_PREFERENCE_KEY = "local_preference_key"
    private const val KEY_LANGUAGE = "key_language"

    @JvmStatic
    fun setLanguage(context: Context, language: String) {
        val prefs = context.getSharedPreferences(
            LOCAL_PREFERENCE_KEY,
            Context.MODE_PRIVATE
        )
        prefs.edit()
            .putString(KEY_LANGUAGE, language)
            .commit()
    }

    @JvmStatic
    fun getLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(
            LOCAL_PREFERENCE_KEY,
            Context.MODE_PRIVATE
        )
        return prefs.getString(KEY_LANGUAGE, Language.ENGLISH_UK.value)
            ?: Language.ENGLISH_UK.value
    }

    //app current locale
    //val appLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
    //device current locale
    //val deviceLocale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]
    @JvmStatic
    fun getDeviceLocale(): Locale {
        return ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]
    }

    //context shouldn't be application-level-context
    @JvmStatic
    fun getAppLocale(context: Context): Locale {
        return ConfigurationCompat.getLocales(context.resources.configuration)[0]
    }

    fun setAppLocale(context: Context): Context {
        val newLocale: Locale = getUserSelectedLocale(context)
        var newContext = context
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= N)
            LocaleList.setDefault(LocaleList(newLocale))
        else
            Locale.setDefault(newLocale)

        configuration.setLocale(newLocale)

        if (Build.VERSION.SDK_INT > N_MR1)
            newContext = context.createConfigurationContext(configuration)
        context.resources.updateConfiguration(configuration, resources.displayMetrics)

        return ContextWrapper(newContext)
    }

    @JvmStatic
    private fun getUserSelectedLocale(context: Context): Locale {
//        return Locale.Builder().setLanguage(getLanguage(context)).build()
        return Locale(getLanguage(context))
    }

    fun Context.appLocale() = getAppLocale(this)
    fun Activity.appLocale() = getAppLocale(this)
    fun Fragment.appLocale() = getAppLocale(requireContext())
}

