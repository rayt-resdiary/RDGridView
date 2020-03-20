package com.example.rdgridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rdgridview.databinding.GridCellBinding

class GridRecyclerRowAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GridRowViewHolder(DataBindingUtil.inflate<GridCellBinding>(LayoutInflater.from(parent.context),R.layout.grid_cell,parent, false))
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridRowViewHolder).textField.text = "$position"
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}

class GridRowViewHolder(view: View) : RecyclerView.ViewHolder(view){

    var textField:TextView = itemView.findViewById(R.id.text_field_view)

    constructor(bind:GridCellBinding):this(bind.root)
}