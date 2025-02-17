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

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Раскомментируйте нужный вариант
         */
        startXmlPracticum() // «традиционный» android (XML)
//          setContent { // Jetpack Compose
//             MainScreen()
    }

    private fun startXmlPracticum() {
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

        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
}