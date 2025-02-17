package com.example.androidpracticumcustomview

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidpracticumcustomview.ui.theme.CustomContainer

class TraditionalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customContainer = CustomContainer(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        setContentView(customContainer)

        val firstView = TextView(this).apply {
            text = "Top Element"
            setBackgroundColor(Color.RED)
            layoutParams = ViewGroup.LayoutParams(200, 200)
        }

        val secondView = TextView(this).apply {
            text = "Bottom Element"
            setBackgroundColor(Color.GREEN)
            layoutParams = ViewGroup.LayoutParams(200, 200)
        }

        customContainer.addView(firstView)

        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }

}