package com.ahmednts.scylla.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

/**
 * Created by AhmedNTS on 4/22/2017.
 */
object AppLocaleManager {
  private const val PREF_LANGUAGE = "PREF_LANGUAGE"
  const val LANGUAGE_ENGLISH = "en"
  const val LANGUAGE_ARABIC = "ar"

  fun getLocale(context: Context): Context {
    return updateLocaleResources(context, getLanguage(context))
  }

  fun setLocale(
      context: Context,
      language: String
  ) {
    setLanguage(context, language)
    updateLocaleResources(context, language)
  }

  private fun getLanguage(context: Context): String? {
    return PreferenceManager.getDefaultSharedPreferences(context)
        .getString(PREF_LANGUAGE, LANGUAGE_ENGLISH)
  }

  @SuppressLint("ApplySharedPref")
  private fun setLanguage(
      context: Context,
      language: String
  ) {
    PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
        .putString(PREF_LANGUAGE, language)
        .commit()
  }

  private fun updateLocaleResources(
      context: Context,
      language: String?
  ): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val res = context.resources
    val config = Configuration()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      config.setLocale(locale)
      res.updateConfiguration(config, res.displayMetrics)
    } else {
      config.locale = locale
      context.createConfigurationContext(config)
    }

    return context
  }
}
