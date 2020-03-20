package com.example.rdgridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rdgridview.databinding.LeftViewBinding

class RowAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var data:List<LeftData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0->{
                RowViewHolderLeftView(DataBindingUtil.inflate<LeftViewBinding>(LayoutInflater.from(parent.context),R.layout.left_view,parent,false))
            }else->
                RowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false))
        }
    }


    override fun getItemCount(): Int {
        return if(data.size > 0) data.size+1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){ 0 }  else{ 1 }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == 0){
            (holder as RowViewHolderLeftView).bind(data[position])
        }else{

        }
    }

}


class RowViewHolder(view: View): RecyclerView.ViewHolder(view) {

}

class RowViewHolderLeftView(var view: View): RecyclerView.ViewHolder(view) {
   lateinit var leftViewBinding:LeftViewBinding

    constructor(leftViewBinding:LeftViewBinding):this(leftViewBinding.root){
        this.leftViewBinding= leftViewBinding
    }

    fun bind(leftData: LeftData){
        leftViewBinding.leftData = leftData
        leftViewBinding.executePendingBindings()
    }
}