package com.pvasilev.aviasales.presentation

import android.view.animation.Animation

fun Animation.addListener(
    onStart: ((Animation) -> Unit)? = null,
    onRepeat: ((Animation) -> Unit)? = null,
    onEnd: ((Animation) -> Unit)? = null
) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation) {
            onRepeat?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animation) {
            onEnd?.invoke(animation)
        }

        override fun onAnimationStart(animation: Animation) {
            onStart?.invoke(animation)
        }
    })
}