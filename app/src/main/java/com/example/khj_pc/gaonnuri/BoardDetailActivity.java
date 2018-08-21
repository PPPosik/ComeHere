package com.example.khj_pc.gaonnuri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.khj_pc.gaonnuri.Data.Board;

public class BoardDetailActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;
    private Board board;
    private TextView title;
    private TextView text1;
    private TextView text2;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");
        if (board == null) {
            Toast.makeText(this, "정보를 가져오는데 오류발생", Toast.LENGTH_SHORT).show();
            finish();
        }
        sliderLayout = findViewById(R.id.slider);
        for (String s : board.getImageURL()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.image(s).setScaleType(DefaultSliderView.ScaleType.CenterCrop);
            sliderLayout.addSlider(defaultSliderView);
        }

        title = findViewById(R.id.board_detail_title);
        text1 = findViewById(R.id.board_detail_text1);
        text2 = findViewById(R.id.board_detail_text2);
        time = findViewById(R.id.board_detail_time);
        findViewById(R.id.board_detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText(board.getTitle());
        text1.setText(board.getContent());
        time.setText(board.getDate());
    }

}
