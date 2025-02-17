package com.example.androidpracticumcustomview.ui.theme

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val left = (width - childWidth) / 2

            val top = when (i) {
                0 -> 0
                else -> height - childHeight
            }

            child.layout(left, top, left + childWidth, top + childHeight)
        }
    }

    override fun addView(child: View?) {
        if (childCount >= 2) throw IllegalStateException("Maximum 2 children allowed")
        super.addView(child)
        child?.let { prepareChild(it) }
    }

    private fun prepareChild(child: View) {
        child.alpha = 0f

        child.post {
            val index = indexOfChild(child)
            val containerHeight = height.toFloat()
            val childHeight = child.height.toFloat()

            val centerOffset = (containerHeight/2 - childHeight/2)

            child.translationY = when (index) {
                0 -> centerOffset
                else -> -centerOffset
            }

            AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(child, View.ALPHA, 1f).apply {
                        duration = 2000
                    },
                    ObjectAnimator.ofFloat(child, View.TRANSLATION_Y, 0f).apply {
                        duration = 5000
                    }
                )
                start()
            }
        }
    }
}