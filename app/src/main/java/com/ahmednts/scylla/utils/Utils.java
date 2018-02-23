package com.ahmednts.scylla.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.ahmednts.scylla.MyApplication;

/**
 * Created by Ahmed Hashem on 1/25/2018.
 */

public abstract class Utils {

  private Utils() {
  }

  public static String getAppName() {
    try {
      PackageManager pm = MyApplication.getInstance().getPackageManager();
      PackageInfo pi = pm.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
      return pi == null ? "Logger" : pi.applicationInfo.loadLabel(pm).toString();
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
      return "Logger";
    }
  }
}
