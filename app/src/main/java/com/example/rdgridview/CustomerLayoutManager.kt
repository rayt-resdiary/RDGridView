package com.example.rdgridview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CustomerLayoutManager(context:Context):LinearLayoutManager(context){



    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }
}