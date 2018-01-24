package com.ahmednts.scylla.utils;

import com.ahmednts.scylla.BuildConfig;
import timber.log.Timber;

/**
 * Created by Ahmed Hashem on 1/23/2018.
 */

public class Logger {

  private Logger() {
  }

  public static void d(String tag, String msg) {
    if (BuildConfig.DEBUG) Timber.tag(tag).d(msg);
  }
}
