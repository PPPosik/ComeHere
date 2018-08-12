package com.example.khj_pc.gaonnuri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.khj_pc.gaonnuri.Adapter.SearchRecyclerViewAdapter;
import com.example.khj_pc.gaonnuri.Data.Contest;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Contest> contests;
    private SearchRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contests = new ArrayList<>();
        contests.add(new Contest(45, "해커해커톤"));
        contests.add(new Contest(34, "해커-해커톤"));
        contests.add(new Contest(12, "좋은친구들"));
        contests.add(new Contest(6, "디자인 인 앱"));

        recyclerView = findViewById(R.id.search_recyclerview);
        adapter = new SearchRecyclerViewAdapter(this, contests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
