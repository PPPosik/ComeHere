package com.example.khj_pc.gaonnuri;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.khj_pc.gaonnuri.Adapter.ChatRecyclerViewAdapter;
import com.example.khj_pc.gaonnuri.Data.Board;
import com.example.khj_pc.gaonnuri.Data.Chat;

import java.util.ArrayList;

public class CommentActivity extends Activity {

    private Board board;
    private ArrayList<Chat> comments;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");

        if (board == null) {
            Toast.makeText(this, "문제발생", Toast.LENGTH_SHORT).show();
            return;
        }
        board.setComments(new ArrayList<Chat>());
        recyclerView = findViewById(R.id.comment_recyclerview);
        comments = board.getComments();
        comments.add(new Chat("장원준(운영자)", "2h ago", "참가자 여러분들께서 피드에 자유롭게 질문을 남기셔도 됩니다."));
        comments.add(new Chat("배현빈", "2h ago", "네 알겠습니다."));
        comments.add(new Chat("익명", "2h ago", "7시부터는"));

        ChatRecyclerViewAdapter adapter = new ChatRecyclerViewAdapter(this, comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

    }
}
