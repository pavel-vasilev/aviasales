package com.pvasilev.aviasales.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val offset: Float) : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply {
        color = Color.LTGRAY
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        (0 until parent.childCount - 1)
            .map { parent.getChildAt(it) }
            .forEach {
                canvas.drawLine(offset, it.bottom.toFloat(), it.right.toFloat(), it.bottom.toFloat(), paint)
            }
    }
}