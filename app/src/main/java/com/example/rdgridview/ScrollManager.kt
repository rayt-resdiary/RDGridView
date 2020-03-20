package com.example.rdgridview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollManager:RecyclerView.OnScrollListener() {

    var scrollableViews:MutableList<RecyclerView> = mutableListOf()
    var mainGridRecyclerView:RecyclerView? = null
    var scrollState:Int = 0
    var offSetLeft:Int = 0
    var visibleChildPosition:Int = 0
//
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleChildPosition = (mainGridRecyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        println("post:$visibleChildPosition")
        offSetLeft = mainGridRecyclerView?.computeHorizontalScrollExtent()?:0
        for(rv in scrollableViews){
            rv.scrollBy(dx,0)
        }
//        mainGridRecyclerView?.onScrolled(0,dy)
    }


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        scrollState = newState
        println("scrollstate:$newState")
    }

    fun scroll(dx: Int, dy: Int){
        mainGridRecyclerView?.scrollBy(dx,0)

//        for(rv in scrollableViews){
//            rv.scrollBy(dx,0)
//            rv.layoutManager?.scrollHorizontallyBy(dx,rv.Recycler(),RecyclerView.State())
//        }
    }

    fun stop(){
        mainGridRecyclerView?.stopScroll()
        for(rv in scrollableViews){
            rv.stopScroll()
        }
    }

    fun fling(velX:Int){
//        for(rv in scrollableViews){
//            rv.fling(velX,0)
//        }
//        mainGridRecyclerView?.fling(velX,0)
    }
}