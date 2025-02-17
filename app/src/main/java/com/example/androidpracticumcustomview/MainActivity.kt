package com.example.androidpracticumcustomview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val composeButton = findViewById<Button>(R.id.compose_button)
        val traditionalButton = findViewById<Button>(R.id.traditional_button)

        composeButton.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }

        traditionalButton.setOnClickListener {
            startActivity(Intent(this, TraditionalActivity::class.java))
        }
    }
}