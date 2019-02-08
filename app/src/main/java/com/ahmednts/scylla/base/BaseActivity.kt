package com.ahmednts.scylla.base

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ahmednts.scylla.utils.AppLocaleManager

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(AppLocaleManager.getLocale(newBase))
  }

  fun drawBehindStatusBar() {
    window.decorView.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = getStatusBarColor()
//        window.navigationBarColor = Color.TRANSPARENT
      } else {
        window.setFlags(
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
      }
    }
  }

  fun setFirstChildFitsSystemWindows() {
    findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0)?.fitsSystemWindows = true
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun setStatusBarStyle(isLight: Boolean) {
    if (isLight) window.decorView.systemUiVisibility =
      window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    else window.decorView.systemUiVisibility =
      window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
  }

  @ColorInt
  private fun getStatusBarColor(): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(android.R.color.transparent, typedValue, true)
    return typedValue.data
  }
}