package com.example.khj_pc.gaonnuri.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Data.Chat;
import com.example.khj_pc.gaonnuri.R;

import java.util.ArrayList;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder> {

    private Context context;
    private ArrayList<Chat> chats;

    public ChatRecyclerViewAdapter(Context context, ArrayList<Chat> chats) {
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatRecyclerViewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.root.setBackgroundColor(Color.parseColor(position % 2 == 0 ? "#f5f9ff" : "#ffffff"));
        holder.username.setText(chat.getUserName());
        holder.time.setText(chat.getTime());
        holder.content.setText(chat.getContent());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ChatRecyclerViewViewHolder extends RecyclerView.ViewHolder {

        View root;
        TextView username, time, content;


        public ChatRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            username = itemView.findViewById(R.id.item_chat_username);
            time = itemView.findViewById(R.id.item_chat_time);
            content = itemView.findViewById(R.id.item_chat_content);

        }

    }

}
