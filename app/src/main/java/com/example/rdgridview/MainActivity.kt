package com.example.rdgridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mVelocityTracker: VelocityTracker? = null
        var scrollManager = ScrollManager()
        var flingManager = FlingManager()
        var adapter = GridRecyclerAdapter()
        adapter.scrollManager = scrollManager
//        adapter.flingManager = flingManager
        var listOfData = mutableListOf<LeftData>()
        for (i in 0..500) {
            var leftData = LeftData("Table $i", "$i")
            for (i in 0..100) {
                leftData.listOfTimes.add(i)
            }
            listOfData.add(leftData)
        }
//        scrollManager.mainGridRecyclerView = main_recycler
        main_recycler.addOnScrollListener(scrollManager)
        adapter.leftData = listOfData
        main_recycler.adapter = adapter
        main_recycler.layoutManager = CustomerLayoutManager(this)

        var flingX = 0f
        var flingY = 0f
        var gestureDetectorCompat =
            GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
//                println("distanceX:${distanceX}")
//                    scrollManager.scroll(distanceX.toInt(), 0)
                    return super.onScroll(e1, e2, distanceX, distanceY)
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
//                    flingManager.fling(-flingX.toInt(),0)
                    scrollManager.fling(-velocityX.toInt())

                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })

//        main_grid.setOnTouchListener { v, event ->
//                        when(event.action) {
//                            MotionEvent.ACTION_DOWN -> {
//                                // Reset the velocity tracker back to its initial state.
//                                mVelocityTracker?.clear()
//                                // If necessary retrieve a new VelocityTracker object to watch the
//                                // velocity of a motion.
//                                mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
//                                // Add a user's movement to the tracker.
//                                mVelocityTracker?.addMovement(event)
//                            }
//                            MotionEvent.ACTION_MOVE -> {
//                                val pointerId: Int = event.getPointerId(event.actionIndex)
//
//                                mVelocityTracker?.apply {
//                                    val pointerId: Int = event.getPointerId(event.actionIndex)
//                                    addMovement(event)
//                                    // When you want to determine the velocity, call
//                                    // computeCurrentVelocity(). Then call getXVelocity()
//                                    // and getYVelocity() to retrieve the velocity for each pointer ID.
//                                    computeCurrentVelocity(1000)
//                                    // Log velocity of pixels per second
//                                    // Best practice to use VelocityTrackerCompat where possible.
//                                    println("X velocity: ${getXVelocity(pointerId)}")
//                                    println("Y velocity: ${getYVelocity(pointerId)}")
//                                    scrollManager.scroll(getXVelocity(pointerId),getYVelocity(pointerId))
//                                }
//                            }
//                            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                                // Return a VelocityTracker object back to be re-used by others.
//                                mVelocityTracker?.recycle()
//                                mVelocityTracker = null
//                            }
//                        }
//            var startX = 0f
//            var startY = 0f
//            when(event.action){
//                MotionEvent.ACTION_DOWN->{
//                    startX = event.x
//                    startY = event.y
//                }
//                MotionEvent.ACTION_UP->{
//                    scrollManager.scroll(event.x.toInt(),event.y.toInt())
//                }
//            }
//            gestureDetector.onTouchEvent(event)
//            true
//        }


        main_recycler.setOnTouchListener { v, event ->
            gestureDetectorCompat.onTouchEvent(event)
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {

                    scrollManager.stop()
                    mVelocityTracker?.clear()
                    // If necessary retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                    // Add a user's movement to the tracker.
                    mVelocityTracker?.addMovement(event)
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.historySize > 0) {
                        var dx = event.getHistoricalAxisValue(0, 0) - event.x
                        scrollManager.scroll(dx.toInt(), 0)
                    }
                    mVelocityTracker?.apply {
                        val pointerId: Int = event.getPointerId(event.actionIndex)
                        addMovement(event)
                        // When you want to determine the velocity, call
                        // computeCurrentVelocity(). Then call getXVelocity()
                        // and getYVelocity() to retrieve the velocity for each pointer ID.
                        computeCurrentVelocity(1000)
                        flingX = getXVelocity(pointerId)
//                        if(scrollManager.scrollState != 1) {
//                            scrollManager.fling(getXVelocity(pointerId).toInt())
//                            flingManager.fling(-flingX.toInt(), 0)
//                        }
                        // Log velocity of pixels per second
                        // Best practice to use VelocityTrackerCompat where possible.
//                        println("X velocity: ${getXVelocity(pointerId)}")
//                        println("Y velocity: ${getYVelocity(pointerId)}")
                    }

                }
            }
            false
        }
    }
}
