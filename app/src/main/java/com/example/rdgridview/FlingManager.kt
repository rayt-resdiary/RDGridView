package com.example.rdgridview

import androidx.recyclerview.widget.RecyclerView

class FlingManager(): RecyclerView.OnFlingListener(){

    var mainFlingView:RecyclerView? = null
    var flingableViews:MutableList<RecyclerView> = mutableListOf()


    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
        for(rv in flingableViews){
            rv.fling(velocityX,0)
        }
        return false
    }

    fun fling(velX:Int, velY:Int){
        mainFlingView?.fling(velX,0)

    }
}