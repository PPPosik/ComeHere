package com.example.khj_pc.gaonnuri.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khj_pc.gaonnuri.Data.CircleChart;
import com.example.khj_pc.gaonnuri.R;

import java.util.ArrayList;

public class ChartRecyclerViewAdapter extends RecyclerView.Adapter<ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder> {
    private Context context;
    private ArrayList<CircleChart> datas;
    public  int Position = 0;

    public ChartRecyclerViewAdapter(Context context, ArrayList<CircleChart> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart_circle_chart, parent, false);
        return new ChartRecyclerViewAdapter.ChartRecyclerViewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartRecyclerViewViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ChartRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        View root;


        public ChartRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;


        }

    }

}
