package com.ahmednts.scylla;

import android.app.Application;
import android.os.StrictMode;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by AhmedNTS on 7/29/2017.
 */

public class MyApplication
    extends Application {

  private static MyApplication INSTANCE;

  public static MyApplication getInstance() {
    return INSTANCE;
  }

  public MyApplication() {
    super();
    INSTANCE = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());

    //if (LeakCanary.isInAnalyzerProcess(this)) {
    //  // This process is dedicated to LeakCanary for heap analysis.
    //  // You should not init your app in this process.
    //  return;
    //}
    //
    //LeakCanary.install(this);

    Timber.plant(new Timber.DebugTree());

    setStrictMode();
  }

  private void setStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
          .detectDiskWrites()
          .detectNetwork()   // or .detectAll() for all detectable problems
          .penaltyLog()
          .build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
          .detectLeakedClosableObjects()
          .penaltyLog()
          .penaltyDeath()
          .build());
    }
  }
}
