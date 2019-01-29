package com.ahmednts.scylla

import android.app.Application
import android.os.StrictMode
import timber.log.Timber

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

    //if (LeakCanary.isInAnalyzerProcess(this)) {
    //  // This process is dedicated to LeakCanary for heap analysis.
    //  // You should not init your app in this process.
    //  return;
    //}
    //
    //LeakCanary.install(this);

    Timber.plant(Timber.DebugTree())
  }

  private fun setStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
          StrictMode.ThreadPolicy.Builder().detectDiskReads()
              .detectDiskWrites()
              .detectNetwork()   // or .detectAll() for all detectable problems
              .penaltyLog()
              .build()
      )
      StrictMode.setVmPolicy(
          StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
              .detectLeakedClosableObjects()
              .penaltyLog()
              .penaltyDeath()
              .build()
      )
    }
  }
}
