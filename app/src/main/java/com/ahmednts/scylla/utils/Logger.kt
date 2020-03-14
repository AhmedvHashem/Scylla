package com.ahmednts.scylla.utils

import android.os.Build
import android.util.Log
import com.ahmednts.scylla.BuildConfig
import timber.log.Timber
import java.util.regex.Pattern

interface Logger {

  fun v(message: String, vararg args: Any?)
  fun v(t: Throwable, message: String, vararg args: Any?)
  fun v(t: Throwable)

  fun d(message: String, vararg args: Any?)
  fun d(t: Throwable, message: String, vararg args: Any?)
  fun d(t: Throwable)

  fun i(message: String, vararg args: Any?)
  fun i(t: Throwable, message: String, vararg args: Any?)
  fun i(t: Throwable)

  fun w(message: String, vararg args: Any?)
  fun w(t: Throwable, message: String, vararg args: Any?)
  fun w(t: Throwable)

  fun e(message: String, vararg args: Any?)
  fun e(t: Throwable, message: String, vararg args: Any?)
  fun e(t: Throwable)
}

object AppLogger : Logger {
  fun setup() {
    if (BuildConfig.DEBUG)
      Timber.plant(DebugTree())
  }

  fun setupForTests() {
    Timber.plant(TestTree())
  }

  override fun v(message: String, vararg args: Any?) {
    Timber.v(message, *args)
  }

  override fun v(t: Throwable, message: String, vararg args: Any?) {
    Timber.v(t, message, *args)
  }

  override fun v(t: Throwable) {
    Timber.v(t)
  }

  override fun d(message: String, vararg args: Any?) {
    Timber.d(message, *args)
  }

  override fun d(t: Throwable, message: String, vararg args: Any?) {
    Timber.d(t, message, *args)
  }

  override fun d(t: Throwable) {
    Timber.d(t)
  }

  override fun i(message: String, vararg args: Any?) {
    Timber.i(message, *args)
  }

  override fun i(t: Throwable, message: String, vararg args: Any?) {
    Timber.i(t, message, *args)
  }

  override fun i(t: Throwable) {
    Timber.i(t)
  }

  override fun w(message: String, vararg args: Any?) {
    Timber.w(message, *args)
  }

  override fun w(t: Throwable, message: String, vararg args: Any?) {
    Timber.w(t, message, *args)
  }

  override fun w(t: Throwable) {
    Timber.w(t)
  }

  override fun e(message: String, vararg args: Any?) {
    Timber.e(message, *args)
  }

  override fun e(t: Throwable, message: String, vararg args: Any?) {
    Timber.e(t, message, *args)
  }

  override fun e(t: Throwable) {
    Timber.e(t)
  }
}

private class DebugTree : Timber.DebugTree() {
  companion object {
    private const val MAX_TAG_LENGTH = 23
    private const val CALL_STACK_INDEX = 7
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
  }

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    super.log(priority, createClassTag(), message, t)
  }

  private fun createClassTag(): String {
    val stackTrace = Throwable().stackTrace
    if (stackTrace.size <= CALL_STACK_INDEX) {
      throw IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?")
    }
    var tag = stackTrace[CALL_STACK_INDEX].className
    val m = ANONYMOUS_CLASS.matcher(tag)
    if (m.find()) {
      tag = m.replaceAll("")
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1)
    // Tag length limit was removed in API 24.
    return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) tag
    else tag.substring(0, MAX_TAG_LENGTH)
  }
}

private class TestTree : Timber.DebugTree() {
  companion object {
    private const val MAX_TAG_LENGTH = 23
    private const val CALL_STACK_INDEX = 7
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
  }

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    print("${createClassTag()} $message")
  }

  private fun createClassTag(): String {
    val stackTrace = Throwable().stackTrace
    if (stackTrace.size <= CALL_STACK_INDEX) {
      throw IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?")
    }
    var tag = stackTrace[CALL_STACK_INDEX].className
    val m = ANONYMOUS_CLASS.matcher(tag)
    if (m.find()) {
      tag = m.replaceAll("")
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1).substringBefore('$')
    // Tag length limit was removed in API 24.
    return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) tag
    else tag.substring(0, MAX_TAG_LENGTH)
  }
}

private class CrashlyticsTree : Timber.Tree() {

  override fun isLoggable(tag: String?, priority: Int): Boolean {
    return priority == Log.ERROR
  }

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
//        Crashlytics.log(message)
  }
}