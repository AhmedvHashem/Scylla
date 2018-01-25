package com.ahmednts.scylla.utils;

import android.util.Log;
import com.ahmednts.scylla.BuildConfig;
import timber.log.Timber;

/**
 * Created by Ahmed Hashem on 1/23/2018.
 */

public abstract class Logger {

  private Logger() {
  }

  private static boolean isLoggable(int priority) {
    return BuildConfig.DEBUG || priority == Log.ERROR || priority == Log.WARN;
  }

  public static Timber.Tree tag(String tag) {
    return Timber.tag(tag);
  }

  public static void v(String msg, Object... args) {
    if (isLoggable(Log.VERBOSE)) Timber.tag(Utils.getAppName()).d(msg, args);
  }

  public static void i(String msg, Object... args) {
    if (isLoggable(Log.INFO)) Timber.tag(Utils.getAppName()).d(msg, args);
  }

  public static void d(String msg, Object... args) {
    if (isLoggable(Log.DEBUG)) Timber.tag(Utils.getAppName()).d(msg, args);
  }

  public static void e(String msg, Object... args) {
    if (isLoggable(Log.ERROR)) Timber.tag(Utils.getAppName()).d(msg, args);
  }

  public static void w(String msg, Object... args) {
    if (isLoggable(Log.WARN)) Timber.tag(Utils.getAppName()).d(msg, args);
  }

  public static void a(String msg, Object... args) {
    if (isLoggable(Log.ASSERT)) Timber.tag(Utils.getAppName()).d(msg, args);
  }
}
