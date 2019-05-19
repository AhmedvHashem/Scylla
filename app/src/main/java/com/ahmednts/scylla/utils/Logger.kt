package com.ahmednts.scylla.utils

import android.util.Log
import com.ahmednts.scylla.BuildConfig
import timber.log.Timber

/**
 * Created by Ahmed Hashem on 1/23/2018.
 */

object Logger {

  private fun isLoggable(priority: Int): Boolean {
    return BuildConfig.DEBUG || priority == Log.ERROR || priority == Log.WARN
  }

  fun tag(tag: String): Timber.Tree {
    return Timber.tag(tag)
  }

  fun v(msg: String, vararg args: Any) {
    if (isLoggable(Log.VERBOSE)) Timber.tag(Utils.appName).d(msg, *args)
  }

  fun i(msg: String, vararg args: Any) {
    if (isLoggable(Log.INFO)) Timber.tag(Utils.appName).d(msg, *args)
  }

  fun d(msg: String, vararg args: Any) {
    if (isLoggable(Log.DEBUG)) Timber.tag(Utils.appName).d(msg, *args)
  }

  fun e(msg: String, vararg args: Any) {
    if (isLoggable(Log.ERROR)) Timber.tag(Utils.appName).d(msg, *args)
  }

  fun w(msg: String, vararg args: Any) {
    if (isLoggable(Log.WARN)) Timber.tag(Utils.appName).d(msg, *args)
  }

  fun a(msg: String, vararg args: Any) {
    if (isLoggable(Log.ASSERT)) Timber.tag(Utils.appName).d(msg, *args)
  }
}
