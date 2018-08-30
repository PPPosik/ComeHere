package com.example.khj_pc.gaonnuri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Activity.BoardDetailActivity;
import com.example.khj_pc.gaonnuri.Data.Board;
import com.example.khj_pc.gaonnuri.R;

import java.util.ArrayList;

public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<BoardRecyclerViewAdapter.BoardRecyclerViewViewHolder> {

    private Context context;
    private ArrayList<Board> boards;

    public BoardRecyclerViewAdapter(Context context, ArrayList<Board> Boards) {
        this.context = context;
        this.boards = Boards;
    }


    @NonNull
    @Override
    public BoardRecyclerViewAdapter.BoardRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new BoardRecyclerViewViewHolder(itemView);
    }

    private int getNoticeCnt() {
        int ret = 0;
        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).getType() == 0) ret++;
            else break;
        }
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardRecyclerViewAdapter.BoardRecyclerViewViewHolder holder, int position) {
        final Board board = boards.get(position);
        if (board.getType() == 0) { //type 0이면 공지글
            holder.root.setBackgroundColor(Color.parseColor("#cadffa"));
        } else {
            int idx = position - getNoticeCnt();
            holder.root.setBackgroundColor(Color.parseColor(idx % 2 == 1 ? "#f5f9ff" : "#ffffff"));
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BoardDetailActivity.class);
                intent.putExtra("board", board);
                context.startActivity(intent);
            }
        });
        holder.title.setText(board.getTitle());
        holder.author.setText(board.getAuthor());
        holder.like.setText(String.valueOf(board.getLikes()));

    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public static class BoardRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        View root;
        TextView title, author, like;


        public BoardRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            title = itemView.findViewById(R.id.item_board_title);
            author = itemView.findViewById(R.id.item_board_author);
            like = itemView.findViewById(R.id.item_board_like);

        }

    }

}
