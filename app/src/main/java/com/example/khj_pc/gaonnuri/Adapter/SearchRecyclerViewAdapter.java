package com.example.khj_pc.gaonnuri.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Data.Contest;
import com.example.khj_pc.gaonnuri.R;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewViewHolder> {

    private Context context;
    private ArrayList<Contest> contests;

    public SearchRecyclerViewAdapter(Context context, ArrayList<Contest> contests) {
        this.context = context;
        this.contests = contests;
    }

    @NonNull
    @Override
    public SearchRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchRecyclerViewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewViewHolder holder, int position) {
        Contest contest = contests.get(position);
        holder.ppl.setText(String.valueOf(contest.getPpl()));
        holder.name.setText(contest.getName());
    }

    @Override
    public int getItemCount() {
        return contests.size();
    }

    public static class SearchRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        View root;
        TextView name, ppl;


        public SearchRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            ppl = itemView.findViewById(R.id.item_search_ppl);
            name = itemView.findViewById(R.id.item_search_name);
        }

    }

}
