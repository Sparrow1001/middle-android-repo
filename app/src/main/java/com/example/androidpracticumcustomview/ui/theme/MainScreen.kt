package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/*
Задание:
Реализуйте необходимые компоненты.
*/

@Composable
fun MainScreen() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            CustomContainerCompose(
                firstChild = {
                    Text(
                        "Top Element",
                        fontSize = 24.sp,
                        modifier = Modifier.background(color = Color.Red)
                    )
                },
                secondChild = {
                    Text(
                        "Bottom Element",
                        fontSize = 24.sp,
                        modifier = Modifier.background(color = Color.Green)
                    )
                }
            )
        }
    }
}