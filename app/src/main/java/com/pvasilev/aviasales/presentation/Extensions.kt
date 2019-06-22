package com.pvasilev.aviasales.presentation

import android.view.MenuItem
import android.view.animation.Animation
import androidx.appcompat.widget.SearchView

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

fun SearchView.setOnQueryTextChangeListener(action: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }

        override fun onQueryTextChange(query: String): Boolean {
            action(query)
            return true
        }
    })
}

fun MenuItem.setOnActionCollapseListener(action: () -> Unit) {
    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            action()
            return true
        }
    })
}