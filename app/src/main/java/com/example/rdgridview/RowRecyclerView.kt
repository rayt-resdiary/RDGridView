package com.example.rdgridview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RowRecyclerView(context: Context, attrs:AttributeSet?, def:Int): RecyclerView(context,attrs,def) {

    constructor(context: Context,attrs:AttributeSet?):this(context,attrs,0)

    constructor(context: Context):this(context,null,0 )

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        performClick()
        return false
    }
}