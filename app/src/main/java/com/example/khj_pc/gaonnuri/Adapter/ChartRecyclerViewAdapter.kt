package com.example.khj_pc.gaonnuri.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.khj_pc.gaonnuri.Data.CircleChart
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.item_chart_circle_chart.view.*

class ChartRecyclerViewAdapter(private val context: Context, private val datas: ArrayList<CircleChart>) : RecyclerView.Adapter<ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder>() {
    var Position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chart_circle_chart, parent, false)
        return ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChartRecyclerViewViewHolder, position: Int) {
        val data = datas[position]
        holder.itemView.title.setText(data.title)
        holder.itemView.content.setText(data.content)
        holder.itemView.donut_progress.setDonut_progress(data.percent.toString())
    }


    override fun getItemCount(): Int {
        return datas.size
    }

    class ChartRecyclerViewViewHolder(internal var root: View) : RecyclerView.ViewHolder(root)

}
