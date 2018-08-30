package com.example.khj_pc.gaonnuri.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.support.percent.PercentLayoutHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khj_pc.gaonnuri.Data.SurveyResult;
import com.example.khj_pc.gaonnuri.R;

import java.util.ArrayList;

public class SurveyChartActivity extends Activity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_chart);

        linearLayout = findViewById(R.id.survey_create_linear_layout);

        ArrayList<SurveyResult> results = new ArrayList<>();
        results.add(new SurveyResult.Text("1. 본인의 이름은?(ex 홍길동)", "정승욱 정승욱1 정승욱_악마버전 정승욱_코딩충 정승욱_스"));
        results.add(new SurveyResult.OX("2. 다시 행사를 개최한다면 주말이었으면 좋겠다.", new int[]{78, 22}));
        results.add(new SurveyResult.Multiple("4. 식사중 가장 불만족 스러웠던 식사는?", new String[]{"1.아침", "2.점심", "3.저녁", "4.야식"}, new int[]{78, 12, 6, 4}));

        for (int i = 0; i < results.size(); i++) {
            SurveyResult s = results.get(i);
            if (s instanceof SurveyResult.Multiple) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_chart_multiple, linearLayout, true);
                View v = linearLayout.getChildAt(i);
                LinearLayout linearLayout1 = v.findViewById(R.id.survey_content_multiple_chart);
                for (int ii = 0; ii < 4; ii++) {
                    float p = ((SurveyResult.Multiple) s).getPercent()[ii];
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true);
                    View vvv = linearLayout1.getChildAt(ii);
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_text)).setText(((SurveyResult.Multiple) s).getContent()[ii]);
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_value)).setText(String.valueOf(((SurveyResult.Multiple) s).getPercent()[ii]));
                    View vv = vvv.findViewById(R.id.item_chart_keyword_bar);
                    ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = p /100* 2 / 3;
//                    ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = ((float) k.getNum() / max) * 2 / 3;


                }
                EditText e = v.findViewById(R.id.survey_title_multiple);
                e.setText(s.getTitle());
                e.setKeyListener(null);

            } else if (s instanceof SurveyResult.OX) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_chart_ox, linearLayout, true);
                View v = linearLayout.getChildAt(i);
                LinearLayout linearLayout1 = v.findViewById(R.id.survey_content_ox_chart);

                {
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true);
                    View vvv = linearLayout1.getChildAt(0);
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_text)).setText("그렇다");
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_value)).setText(String.valueOf(((SurveyResult.OX) s).getPercent()[0]));
                    View vv = vvv.findViewById(R.id.item_chart_keyword_bar);
                    ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = ((float) ((SurveyResult.OX) s).getPercent()[0])/100 * 2 / 3;
                    Log.d("brfuck", "onCreate: " + ((float) ((SurveyResult.OX) s).getPercent()[0]) * 2 / 3);
                }

                {
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true);
                    View vvv = linearLayout1.getChildAt(1);
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_text)).setText("아니다");
                    ((TextView) vvv.findViewById(R.id.item_chart_keyword_value)).setText(String.valueOf(((SurveyResult.OX) s).getPercent()[1]));
                    View vv = vvv.findViewById(R.id.item_chart_keyword_bar);
                    ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = ((float) ((SurveyResult.OX) s).getPercent()[1])/100 * 2 / 3;

                }
                EditText e = v.findViewById(R.id.survey_title_ox);
                e.setText(s.getTitle());
                e.setKeyListener(null);

            } else {
                LayoutInflater.from(this).inflate(R.layout.item_survey_text, linearLayout, true);
                View v = linearLayout.getChildAt(i);
                EditText e = v.findViewById(R.id.content);
                e.setText(((SurveyResult.Text) s).getContent());
                e.setKeyListener(null);
                e = v.findViewById(R.id.survey_title_text);
                e.setText(s.getTitle());
                e.setKeyListener(null);
            }
        }


    }

}
