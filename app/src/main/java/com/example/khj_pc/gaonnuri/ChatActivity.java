package com.example.khj_pc.gaonnuri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.khj_pc.gaonnuri.Adapter.ChatRecyclerViewAdapter;
import com.example.khj_pc.gaonnuri.Data.Chat;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Chat> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.chat_recyclerview);
        chats = new ArrayList<>();
        chats.add(new Chat("장원준(운영자)", "2h ago", "참가자 여러분들께서 피드에 자유롭게 질문을 남기셔도 됩니다."));
        chats.add(new Chat("배현빈", "2h ago", "네 알겠습니다."));
        chats.add(new Chat("익명", "2h ago", "7시부터는"));
        ChatRecyclerViewAdapter adapter = new ChatRecyclerViewAdapter(this, chats);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

    }


}