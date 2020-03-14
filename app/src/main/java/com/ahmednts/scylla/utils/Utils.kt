package com.ahmednts.scylla.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlin.math.roundToInt

/**
 * Created by Ahmed Hashem on 1/25/2018.
 */

object Utils {

  fun dp2px(dp: Int): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (dp * density).roundToInt()
  }

  fun hideSoftKeyboard(activity: Activity, view: View) {
    //Set up touch listener for non-text box views to hide keyboard.
    if (view !is EditText /* || !(view instanceof AutoCompleteTextView)*/) {
      view.setOnTouchListener { _, _ ->
        hideSoftKeyboard(activity)
        false
      }
    }
    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
      for (i in 0 until view.childCount) {
        val innerView = view.getChildAt(i)
        hideSoftKeyboard(activity, innerView)
      }
    }
  }

  fun hideSoftKeyboard(activity: Activity) {
    try {
      val mgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      mgr.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}
