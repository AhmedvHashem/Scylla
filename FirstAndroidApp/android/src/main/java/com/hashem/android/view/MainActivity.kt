package com.hashem.android.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hashem.android.MySingleton
import com.hashem.android.R

/**
 * Created by Hashem.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MySingleton.getInstance {
            findViewById<TextView>(R.id.btn).text = "Hi from Singleton"
        }

        findViewById<TextView>(R.id.btn).setOnClickListener { v: View? ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        Runtime.getRuntime().gc()

        Bundle().apply {
            putString("key", "value")
        }
    }
}
