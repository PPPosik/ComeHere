package com.example.khj_pc.gaonnuri;

import android.os.Bundle;
import android.app.Activity;
import android.support.percent.PercentLayoutHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Adapter.ChartRecyclerViewAdapter;
import com.example.khj_pc.gaonnuri.Data.CircleChart;
import com.example.khj_pc.gaonnuri.Data.KeywordChart;

import java.util.ArrayList;

public class ChartActivity extends Activity {

    private RecyclerView recyclerView;
    private ChartRecyclerViewAdapter adapter;
    private ArrayList<CircleChart> datas;
    private LinearLayout linearLayout;
    private ArrayList<KeywordChart> keywordCharts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        linearLayout = findViewById(R.id.keyword_chart_linearlayout);
        recyclerView = findViewById(R.id.chart_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        datas = new ArrayList<>();
        datas.add(new CircleChart("음료수", "솔의 눈 싫어요", 76));
        datas.add(new CircleChart("음료수", "솔의 눈 싫어요", 76));
        datas.add(new CircleChart("음료수", "솔의 눈 싫어요", 76));

        adapter = new ChartRecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.notifyDataSetChanged();

        keywordCharts = new ArrayList<>();
        keywordCharts.add(new KeywordChart("금액", 1420));
        keywordCharts.add(new KeywordChart("주차장", 812));
        keywordCharts.add(new KeywordChart("만지", 316));
        keywordCharts.add(new KeywordChart("만지2", 1316));
        keywordCharts.add(new KeywordChart("만지3", 3216));
        int max = -1;
        for (KeywordChart k : keywordCharts)
            max = Math.max(k.getNum(), max);


        for (KeywordChart k : keywordCharts) {
            View v = LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, null);
            ((TextView) v.findViewById(R.id.item_chart_keyword_text)).setText(k.getKeyword());
            ((TextView) v.findViewById(R.id.item_chart_keyword_value)).setText(String.valueOf(k.getNum()));
            View vv = v.findViewById(R.id.item_chart_keyword_bar);
            ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = ((float) k.getNum() / max) * 2 / 3;
            linearLayout.addView(v);
        }

        findViewById(R.id.chart_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.Position > 0)
                    recyclerView.smoothScrollToPosition(--adapter.Position);
            }
        });
        findViewById(R.id.chart_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.Position < adapter.getItemCount() - 1) {
                    recyclerView.smoothScrollToPosition(++adapter.Position);

                }

            }
        });

    }

}
