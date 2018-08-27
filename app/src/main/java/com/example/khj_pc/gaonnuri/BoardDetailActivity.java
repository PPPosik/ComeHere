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

import java.util.ArrayList;

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

        final Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");
       /* ArrayList<String> imgUrl = new ArrayList<>();
        imgUrl.add("https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg");
        imgUrl.add("https://images.pexels.com/photos/730896/pexels-photo-730896.jpeg");
        imgUrl.add("https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg");

        board = new Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1, null);

     */
        if (board == null) {
            Toast.makeText(this, "정보를 가져오는데 오류발생", Toast.LENGTH_SHORT).show();
            finish();
        }
        sliderLayout = findViewById(R.id.slider);
        for (String s : board.getImageURL()) {
            String url = "http://ssumon.com:23002/images/" + s;
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.image(url).setScaleType(DefaultSliderView.ScaleType.CenterCrop);
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
        findViewById(R.id.board_detail_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BoardDetailActivity.this, CommentActivity.class);
                intent1.putExtra("board", board);
                startActivity(intent1);
            }
        });

        title.setText(board.getTitle());
        text1.setText(board.getContent());
        time.setText(board.getDate());
    }

}
