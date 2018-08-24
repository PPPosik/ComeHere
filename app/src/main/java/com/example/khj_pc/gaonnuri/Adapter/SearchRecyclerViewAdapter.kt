package com.example.khj_pc.gaonnuri.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.khj_pc.gaonnuri.Data.Contest
import com.example.khj_pc.gaonnuri.Data.Room
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.content_search.view.*
import kotlinx.android.synthetic.main.item_search.view.*

import android.R.attr.name
import android.text.method.TextKeyListener.clear
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList


class SearchRecyclerViewAdapter(private val context: Context, private var rooms: ArrayList<Room>) : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewViewHolder>() {

    private var isFiltered : Boolean = false
    var arrayList : ArrayList<Room> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchRecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewViewHolder, position: Int) {
        val room = arrayList[position]
        if(isFiltered) {
            holder.root.item_search_ppl.text = room.peopleNumMax.toString()
            holder.root.item_search_name.text = room.questionName
        }
    }

    override fun getItemCount(): Int {
        if(isFiltered) {
            return arrayList.size
        } else {
            return 0
        }
    }

    fun setRooms(rooms : ArrayList<Room>) {
        this.rooms = rooms
    }

    // Filter Class
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        arrayList.clear()
        if (charText.isEmpty()) {
            arrayList.addAll(rooms)
        } else {
            for (room in rooms) {
                val name : String? = room.questionName
                if(name != null) {
                    Log.d("size name", name)
                    if (name.toLowerCase().contains(charText)) {
                        arrayList.add(room)
                    }
                }
            }
            isFiltered = true

        }

        Log.d("room size", rooms.size.toString())
        notifyDataSetChanged()
    }


    class SearchRecyclerViewViewHolder(var root: View) : RecyclerView.ViewHolder(root) {}

}
