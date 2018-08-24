package com.example.khj_pc.gaonnuri.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.khj_pc.gaonnuri.Data.Room
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewItemAdapter(val items : List<Room>, val context : Context) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflate = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        val v = ViewHolder(layoutInflate)
        return v
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var room : Room? = items.get(position)
        if(room != null)
            holder.button.text = items.get(position).title
    }
}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val button = view.item_button
}