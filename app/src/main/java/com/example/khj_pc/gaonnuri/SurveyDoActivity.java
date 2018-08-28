package com.example.khj_pc.gaonnuri;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Data.Survey;

import java.util.ArrayList;

public class SurveyDoActivity extends Activity {

    ArrayList<Survey> surveys;
    private LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_do);


        surveys = new ArrayList<>();
        surveys.add(new Survey.SurveyText("1. 본인의 이름은?(ex 홍길동)"));
        surveys.add(new Survey.SurveyOX("2. 다시 행사를 개최한다면 주말이었으면 좋겠다.", true));
        surveys.add(new Survey.SurveyOX("3. 행사 개최자는 잘생겼다.", false));
        surveys.add(new Survey.SurveyMultiple("4. 식사중 가장 불만족 스러웠던 식사는?", new String[]{"아침", "점심", "저녁", "야식1"}));


        linearLayout = findViewById(R.id.survey_create_linear_layout);
        for (int i = 0; i < surveys.size(); i++) {
            Survey s = surveys.get(i);
            Log.d("fuck", "onCreate: " + s.getTitle());
            if (s instanceof Survey.SurveyText) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_text, linearLayout, true);
                final View v = linearLayout.getChildAt(i);
                EditText e = v.findViewById(R.id.survey_title_text);
                e.setText(String.valueOf(i) + s.getTitle());
                e.setKeyListener(null);
            } else if (s instanceof Survey.SurveyMultiple) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_multiple, linearLayout, true);
                final View v = linearLayout.getChildAt(i);

                EditText ee = v.findViewById(R.id.survey_multiple_content1);

                ee.setText(((Survey.SurveyMultiple) s).getContent()[0]);
                ee.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) s).getSelected()[0] ? "#F2F9FE" : "#ffffff"));
                final Survey finalS1 = s;
                final EditText finalEe = ee;
                ee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyMultiple) finalS1).getSelected()[0] = !((Survey.SurveyMultiple) finalS1).getSelected()[0];
                        finalEe.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) finalS1).getSelected()[1] ? "#F2F9FE" : "#ffffff"));
                    }
                });
                ee.setKeyListener(null);

                ee = v.findViewById(R.id.survey_multiple_content2);
                ee.setText(((Survey.SurveyMultiple) s).getContent()[1]);
                ee.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) s).getSelected()[1] ? "#F2F9FE" : "#ffffff"));
                final Survey finalS2 = s;
                ee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyMultiple) finalS1).getSelected()[1] = !((Survey.SurveyMultiple) finalS1).getSelected()[1];
                        finalEe.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) finalS2).getSelected()[1] ? "#F2F9FE" : "#ffffff"));
                    }
                });
                ee.setKeyListener(null);

                ee = v.findViewById(R.id.survey_multiple_content3);
                ee.setText(((Survey.SurveyMultiple) s).getContent()[2]);
                ee.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) s).getSelected()[2] ? "#F2F9FE" : "#ffffff"));
                final Survey finalS3 = s;
                ee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyMultiple) finalS1).getSelected()[2] = !((Survey.SurveyMultiple) finalS1).getSelected()[2];
                        finalEe.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) finalS3).getSelected()[1] ? "#F2F9FE" : "#ffffff"));
                    }
                });
                ee.setKeyListener(null);

                ee = v.findViewById(R.id.survey_multiple_content4);
                ee.setText(((Survey.SurveyMultiple) s).getContent()[3]);
                ee.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) s).getSelected()[3] ? "#F2F9FE" : "#ffffff"));
                final Survey finalS4 = s;
                ee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyMultiple) finalS1).getSelected()[3] = !((Survey.SurveyMultiple) finalS1).getSelected()[3];
                        finalEe.setBackgroundColor(Color.parseColor(((Survey.SurveyMultiple) finalS4).getSelected()[1] ? "#F2F9FE" : "#ffffff"));
                    }
                });
                ee.setKeyListener(null);
                //TODO

                EditText e = v.findViewById(R.id.survey_title_multiple);
                e.setText(s.getTitle());
                e.setKeyListener(null);
            } else {
                LayoutInflater.from(this).inflate(R.layout.item_survey_ox, linearLayout, true);
                final View v = linearLayout.getChildAt(i);

                final TextView yes = v.findViewById(R.id.survey_create_yes);
                final TextView no = v.findViewById(R.id.survey_create_no);
                final Survey finalS = s;
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyOX) finalS).setContent(true);

                        yes.setBackground(getResources().getDrawable(((Survey.SurveyOX) finalS).isContent() ? R.drawable.background_rectangle_light_black_black_content : R.drawable.background_rectangle_light_black_white_content));
                        yes.setTextColor(Color.parseColor(((Survey.SurveyOX) finalS).isContent() ? "#ffffff" : "#172434"));
                        no.setBackground(getResources().getDrawable(((Survey.SurveyOX) finalS).isContent() ? R.drawable.background_rectangle_light_black_white_content : R.drawable.background_rectangle_light_black_black_content));
                        no.setTextColor(Color.parseColor(((Survey.SurveyOX) finalS).isContent() ? "#172434" : "#ffffff"));
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Survey.SurveyOX) finalS).setContent(false);

                        yes.setBackground(getResources().getDrawable(((Survey.SurveyOX) finalS).isContent() ? R.drawable.background_rectangle_light_black_black_content : R.drawable.background_rectangle_light_black_white_content));
                        yes.setTextColor(Color.parseColor(((Survey.SurveyOX) finalS).isContent() ? "#ffffff" : "#172434"));
                        no.setBackground(getResources().getDrawable(((Survey.SurveyOX) finalS).isContent() ? R.drawable.background_rectangle_light_black_white_content : R.drawable.background_rectangle_light_black_black_content));
                        no.setTextColor(Color.parseColor(((Survey.SurveyOX) finalS).isContent() ? "#172434" : "#ffffff"));
                    }
                });

                yes.setBackground(getResources().getDrawable(((Survey.SurveyOX) s).isContent() ? R.drawable.background_rectangle_light_black_black_content : R.drawable.background_rectangle_light_black_white_content));
                yes.setTextColor(Color.parseColor(((Survey.SurveyOX) s).isContent() ? "#ffffff" : "#172434"));
                no.setBackground(getResources().getDrawable(((Survey.SurveyOX) s).isContent() ? R.drawable.background_rectangle_light_black_white_content : R.drawable.background_rectangle_light_black_black_content));
                no.setTextColor(Color.parseColor(((Survey.SurveyOX) s).isContent() ? "#172434" : "#ffffff"));


                EditText e = v.findViewById(R.id.survey_title_ox);
                e.setText(s.getTitle());
                e.setKeyListener(null);

            }


        }


    }

}
