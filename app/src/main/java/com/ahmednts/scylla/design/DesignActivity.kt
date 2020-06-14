package com.ahmednts.scylla.design

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.TranslateAnimation
import com.ahmednts.scylla.R
import kotlinx.android.synthetic.main.activity_design.*

class DesignActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_design)

    buttonUp.setOnClickListener {
      viewToAnimate.post {
        viewToAnimate.showAlertUp(3000)
      }
    }

    buttonDown.setOnClickListener {
      viewToAnimate.showAlertDown(3000)
    }
  }
}

fun View.showAlertUp(duration: Long) {
  clearAnimation()
  show()
  post {
    val animate = TranslateAnimation(
        0f,
        0f,
        0f,
        -height.toFloat())

    animate.duration = 500
    animate.fillAfter = true
    startAnimation(animate)
  }

  postDelayed({
    val animate = TranslateAnimation(
        0f,
        0f,
        -height.toFloat(),
        0f)

    animate.duration = 300
    animate.fillAfter = true
    startAnimation(animate)
  }, duration)
}

fun View.showAlertDown(duration: Long) {
  clearAnimation()
  show()
  post {
    val animate = TranslateAnimation(
        0f,
        0f,
        0f,
        height.toFloat())

    animate.duration = 500
    animate.fillAfter = true
    startAnimation(animate)
  }

  postDelayed({
    val animate = TranslateAnimation(
        0f,
        0f,
        height.toFloat(),
        0f)

    animate.duration = 300
    animate.fillAfter = true
    startAnimation(animate)
  }, duration)
}

fun View.show() {
  this.visibility = View.VISIBLE
}

fun View.hide() {
  this.visibility = View.INVISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}
