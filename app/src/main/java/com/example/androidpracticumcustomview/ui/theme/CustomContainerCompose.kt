package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val containerHeight = maxHeight
        val density = LocalDensity.current

        val (initialOffset, targetOffset) = remember(density, containerHeight) {
            with(density) {
                val offset = containerHeight / 4
                val initial = 0.dp
                Pair(initial.toPx(), offset.toPx())
            }
        }

        val firstAlpha = remember { Animatable(0f) }
        val firstOffsetY = remember { Animatable(initialOffset) }

        val secondAlpha = remember { Animatable(0f) }
        val secondOffsetY = remember { Animatable(initialOffset) }

        LaunchedEffect(Unit) {
            launch {
                firstAlpha.animateTo(1f, animationSpec = tween(2000, easing = LinearEasing))
            }
            launch {
                firstOffsetY.animateTo(-targetOffset, tween(5000, easing = LinearEasing))
            }

            if (secondChild != null) {
                launch {
                    secondAlpha.animateTo(1f, tween(2000, easing = LinearEasing))
                }
                launch {
                    secondOffsetY.animateTo(targetOffset, tween(5000, easing = LinearEasing))
                }
            }
        }

        firstChild?.let { child ->
            Box(
                Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        alpha = firstAlpha.value
                        translationY = firstOffsetY.value
                    }
            ) {
                child()
            }
        }

        secondChild?.let { child ->
            Box(
                Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        alpha = secondAlpha.value
                        translationY = secondOffsetY.value
                    }
            ) {
                child()
            }
        }
    }
}