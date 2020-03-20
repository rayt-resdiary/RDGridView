package com.example.rdgridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rdgridview.databinding.GridRecyclerRowBinding

class GridRecyclerAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var leftData: List<LeftData> = mutableListOf()

    var scrollManager:ScrollManager? = null

    var flingManager:FlingManager? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GridRecyclerRowViewHolder(DataBindingUtil.inflate<GridRecyclerRowBinding>(LayoutInflater.from(parent.context),R.layout.grid_recycler_row,parent,false))
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        scrollManager?.let {
            scrollManager?.mainGridRecyclerView?:run{
                scrollManager?.mainGridRecyclerView =  (holder as GridRecyclerRowViewHolder).recyclerView
                scrollManager?.let{
                    holder.recyclerView.addOnScrollListener(it)
                }
            }

        }
//        if(holder.adapterPosition == 0){
//            flingManager?.let{
//                flingManager?.mainFlingView = (holder as GridRecyclerRowViewHolder).recyclerView
//                (holder as GridRecyclerRowViewHolder).recyclerView.onFlingListener = flingManager
//            }
//        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
//        scrollManager?.let {
//            (holder as GridRecyclerRowViewHolder).recyclerView.removeOnScrollListener(it)
//            holder.recyclerView.onFlingListener = null
//        }
    }

    override fun getItemCount(): Int {
        return if(leftData.isEmpty()) 0 else leftData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridRecyclerRowViewHolder).gridRecyclerRowBinding.leftData = leftData[position]
        if(holder.itemView.tag == null){
            holder.itemView.tag = "Set"
            if(position != 0) {
                scrollManager?.scrollableViews?.add(holder.recyclerView)
//                flingManager?.flingableViews?.add(holder.recyclerView)
            }
            holder.recyclerView.layoutManager = LinearLayoutManager(holder.recyclerView.context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            holder.recyclerView.adapter = GridRecyclerRowAdapter()
            ((holder as GridRecyclerRowViewHolder).recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(scrollManager?.visibleChildPosition?:0)

        }

    }
}

class GridRecyclerRowViewHolder(view: View):RecyclerView.ViewHolder(view){

    lateinit var gridRecyclerRowBinding:GridRecyclerRowBinding
    lateinit var recyclerView:RowRecyclerView

    constructor(binding:GridRecyclerRowBinding):this(binding.root){
        gridRecyclerRowBinding = binding
        recyclerView = binding.rightRecycler
        val itemViewType = 0
        recyclerView.recycledViewPool.setMaxRecycledViews(itemViewType, 0)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(50)
    }

    fun bind(data:LeftData){
        gridRecyclerRowBinding.leftData = data
    }

}