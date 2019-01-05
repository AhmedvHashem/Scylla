package com.ahmednts.scylla.design

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ahmednts.scylla.R
import kotlinx.android.synthetic.main.activity_design.fab
import kotlinx.android.synthetic.main.activity_design.toolbar

class DesignActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_design)
    setSupportActionBar(toolbar)

    drawBehindStatusBar()
    setStatusBarStyle(false)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }

  private fun drawBehindStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
      if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.TRANSPARENT
//        window.navigationBarColor = Color.TRANSPARENT
      } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
      }

//      window.setFlags(
//          WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//          WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
//      )
    }

    findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0)
        ?.fitsSystemWindows = true
  }

  @TargetApi(Build.VERSION_CODES.M)
  private fun setStatusBarStyle(isLight: Boolean) {
    if (isLight)
      window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
          View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    else
      window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
          View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
  }
}
