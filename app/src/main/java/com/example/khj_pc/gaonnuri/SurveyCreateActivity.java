package com.example.khj_pc.gaonnuri;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.khj_pc.gaonnuri.Data.Survey;

import java.util.ArrayList;

public class SurveyCreateActivity extends Activity {

    ArrayList<Survey> surveys;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_create);
        surveys = new ArrayList<>();

        linearLayout = findViewById(R.id.survey_create_linear_layout);

        View vv = findViewById(R.id.survey_create_add);
        if (vv == null) return;
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence info[] = new CharSequence[]{"다지선다", "O/X", "주관식"};


                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyCreateActivity.this);

                builder.setTitle("어떤 설문을 만드시겠습니까?");

                builder.setItems(info, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        addSurvey(which);
                        dialog.dismiss();

                    }

                });

                builder.show();
            }
        });
    }

    public void addSurvey(int type) {
        int layout[] = new int[]{R.layout.item_survey_multiple, R.layout.item_survey_ox, R.layout.item_survey_text};
        LayoutInflater.from(this).inflate(layout[type], linearLayout, true);
        switch (type) {
            case 0:
                surveys.add(new Survey.SurveyMultiple());
                break;
            case 1:
                surveys.add(new Survey.SurveyOX());
                break;
            case 2:
                surveys.add(new Survey.SurveyText());
                break;
        }
//        View v = LayoutInflater.from(this).inflate(layout[type], null);

    }

}
