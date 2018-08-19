package com.example.khj_pc.gaonnuri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.khj_pc.gaonnuri.Adapter.BoardRecyclerViewAdapter;
import com.example.khj_pc.gaonnuri.Data.Board;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<Board> boards;
    private BoardRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.board_recyclerview);
        boards = new ArrayList<>();
        boards.add(new Board("참가자 필독", "장원준(운영자)", "", 120, 0));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        boards.add(new Board("자유게시물", "배현빈", "", 21, 1));
        adapter = new BoardRecyclerViewAdapter(this, boards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
