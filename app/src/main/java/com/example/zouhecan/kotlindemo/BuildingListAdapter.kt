package com.example.zouhecan.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_building_list.view.*

class BuildingListAdapter (val items : List<Building>, val itemClick: BuildingListAdapter.ItemActionListener) : RecyclerView.Adapter<BuildingListAdapter.ViewHolder>() {

    interface ItemActionListener{
        fun onItemClick(v:View, tag:Int)//item点击事件
        fun onItemLongClick(v:View, tag:Int)//item长按事件
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_building_list, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.tag = position
    }

    class ViewHolder(val view: View, val itemClick: BuildingListAdapter.ItemActionListener) : RecyclerView.ViewHolder(view){
        fun bind(building :Building){
            view.building_num.text = building.buildingNum
            itemView.setOnClickListener{
                itemClick.onItemClick(view, view.tag as Int)
            }
            itemView.setOnLongClickListener{
                itemClick.onItemLongClick(view, view.tag as Int)
                return@setOnLongClickListener true
            }
        }
    }

}
