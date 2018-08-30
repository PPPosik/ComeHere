package com.example.khj_pc.gaonnuri.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.khj_pc.gaonnuri.Data.Board;
import com.example.khj_pc.gaonnuri.Data.SingleBoardResult;
import com.example.khj_pc.gaonnuri.R;
import com.example.khj_pc.gaonnuri.Service.PostService;
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.jetbrains.anko.ToastsKt.toast;

public class BoardDetailActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;
    private Board board;
    private TextView title;
    private TextView text1;
    private TextView text2;
    private TextView time;

    private static final String TAG  = BoardDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        board = (Board) intent.getSerializableExtra("board");



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

        loadData();

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


    }

    public void addLikes(View view) {
        Log.e("ASDF4", "ASDF4");
        PostService postService = RetrofitUtil.INSTANCE.getLoginRetrofit(getApplicationContext()).create(PostService.class);
        Call<SingleBoardResult> call = postService.addLike(board.getRoomId(), board.getId());
        call.enqueue(new Callback<SingleBoardResult>() {
            @Override
            public void onResponse(Call<SingleBoardResult> call, Response<SingleBoardResult> response) {
                Log.e("code", Integer.toString(response.code()));
                if(response.body() != null) {
                    switch(response.code()) {
                        case 200:
                            Log.d(TAG, "success to like");
                            break;
                        default:
                            if(response.body().getMessage() != null)
                                Log.e(TAG, response.body().getMessage());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleBoardResult> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        PostService postService = RetrofitUtil.INSTANCE.getLoginRetrofit(getApplicationContext()).create(PostService.class);
        Call<Board> call = postService.getOne(board.getRoomId(), board.getId());
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Log.e("code", Integer.toString(response.code()));
                if(response.body() != null) {
                    switch(response.code()) {
                        case 200:
                            board = response.body();
                            title.setText(board.getTitle());
                            text1.setText(board.getContent());
                            time.setText(board.getDate());
                            break;
                        default:
                                Log.e(TAG, Integer.toString(response.code()));
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Log.e(TAG, t.toString());
            }

        });
    }
}
