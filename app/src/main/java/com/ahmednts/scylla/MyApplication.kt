package com.ahmednts.scylla

import android.app.Application
import android.os.StrictMode
import com.ahmednts.scylla.utils.AppLogger
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

/**
 * Created by AhmedNTS on 7/29/2017.
 */

class MyApplication : Application() {
  companion object {
    private lateinit var INSTANCE: MyApplication

    @JvmStatic
    val instance
      get() = INSTANCE
  }

  init {
    INSTANCE = this
  }

  override fun onCreate() {
    super.onCreate()
    setStrictMode()

    AppLogger.setup()
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
  }

  private fun setStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
          StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork()   // or .detectAll() for all detectable problems
              .penaltyLog().build()
      )
      StrictMode.setVmPolicy(
          StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build()
      )
    }
  }
}
