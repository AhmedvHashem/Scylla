package com.ahmednts.scylla.utils

import android.content.pm.PackageManager
import android.content.res.Resources
import com.ahmednts.scylla.MyApplication

/**
 * Created by Ahmed Hashem on 1/25/2018.
 */

object Utils {

  val appName: String
    get() {
      return try {
        val pm = MyApplication.instance.packageManager
        val pi = pm.getPackageInfo(MyApplication.instance.packageName, 0)
        pi?.applicationInfo?.loadLabel(pm)?.toString() ?: "Logger"
      } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "Logger"
      }
    }

  fun dp2px(dp: Int): Int {
    val density = Resources.getSystem().displayMetrics.density
    return Math.round(dp * density)
  }
}
