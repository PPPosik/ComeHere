package com.example.khj_pc.gaonnuri.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.app.Activity
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import com.example.khj_pc.gaonnuri.Data.Survey
import com.example.khj_pc.gaonnuri.R

import java.util.ArrayList

class SurveyDoActivity : Activity() {

    lateinit var surveys: ArrayList<Survey>
    private var linearLayout: LinearLayout? = null

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_do)


        surveys = ArrayList()
        surveys.add(Survey.SurveyText("1. 본인의 이름은?(ex 홍길동)"))
        surveys.add(Survey.SurveyOX("2. 다시 행사를 개최한다면 주말이었으면 좋겠다.", true))
        surveys.add(Survey.SurveyOX("3. 행사 개최자는 잘생겼다.", false))
        surveys.add(Survey.SurveyMultiple("4. 식사중 가장 불만족 스러웠던 식사는?", arrayOf("아침", "점심", "저녁", "야식1")))


        linearLayout = findViewById(R.id.survey_create_linear_layout)
        for (i in surveys.indices) {
            val s = surveys[i]
            Log.d("fuck", "onCreate: " + s.title)
            if (s is Survey.SurveyText) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_text, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)
                val e = v.findViewById<EditText>(R.id.survey_title_text)
                e.setText(i.toString() + s.getTitle())
                e.keyListener = null
            } else if (s is Survey.SurveyMultiple) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_multiple, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)

                var ee = v.findViewById<EditText>(R.id.survey_multiple_content1)

                ee.setText(s.content[0])
                ee.setBackgroundColor(Color.parseColor(if (s.selected[0]) "#F2F9FE" else "#ffffff"))
                val finalEe = ee
                ee.setOnClickListener {
                    s.selected[0] = !s.selected[0]
                    finalEe.setBackgroundColor(Color.parseColor(if (s.selected[1]) "#F2F9FE" else "#ffffff"))
                }
                ee.keyListener = null

                ee = v.findViewById(R.id.survey_multiple_content2)
                ee.setText(s.content[1])
                ee.setBackgroundColor(Color.parseColor(if (s.selected[1]) "#F2F9FE" else "#ffffff"))
                ee.setOnClickListener {
                    s.selected[1] = !s.selected[1]
                    finalEe.setBackgroundColor(Color.parseColor(if (s.selected[1]) "#F2F9FE" else "#ffffff"))
                }
                ee.keyListener = null

                ee = v.findViewById(R.id.survey_multiple_content3)
                ee.setText(s.content[2])
                ee.setBackgroundColor(Color.parseColor(if (s.selected[2]) "#F2F9FE" else "#ffffff"))
                ee.setOnClickListener {
                    s.selected[2] = !s.selected[2]
                    finalEe.setBackgroundColor(Color.parseColor(if (s.selected[1]) "#F2F9FE" else "#ffffff"))
                }
                ee.keyListener = null

                ee = v.findViewById(R.id.survey_multiple_content4)
                ee.setText(s.content[3])
                ee.setBackgroundColor(Color.parseColor(if (s.selected[3]) "#F2F9FE" else "#ffffff"))
                ee.setOnClickListener {
                    s.selected[3] = !s.selected[3]
                    finalEe.setBackgroundColor(Color.parseColor(if (s.selected[1]) "#F2F9FE" else "#ffffff"))
                }
                ee.keyListener = null
                //TODO

                val e = v.findViewById<EditText>(R.id.survey_title_multiple)
                e.setText(s.getTitle())
                e.keyListener = null
            } else {
                LayoutInflater.from(this).inflate(R.layout.item_survey_ox, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)

                val yes = v.findViewById<TextView>(R.id.survey_create_yes)
                val no = v.findViewById<TextView>(R.id.survey_create_no)
                yes.setOnClickListener {
                    (s as Survey.SurveyOX).isContent = true

                    yes.background = resources.getDrawable(if (s.isContent) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                    yes.setTextColor(Color.parseColor(if (s.isContent) "#ffffff" else "#172434"))
                    no.background = resources.getDrawable(if (s.isContent) R.drawable.background_rectangle_light_black_white_content else R.drawable.background_rectangle_light_black_black_content)
                    no.setTextColor(Color.parseColor(if (s.isContent) "#172434" else "#ffffff"))
                }
                no.setOnClickListener {
                    (s as Survey.SurveyOX).isContent = false

                    yes.background = resources.getDrawable(if (s.isContent) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                    yes.setTextColor(Color.parseColor(if (s.isContent) "#ffffff" else "#172434"))
                    no.background = resources.getDrawable(if (s.isContent) R.drawable.background_rectangle_light_black_white_content else R.drawable.background_rectangle_light_black_black_content)
                    no.setTextColor(Color.parseColor(if (s.isContent) "#172434" else "#ffffff"))
                }

                yes.background = resources.getDrawable(if ((s as Survey.SurveyOX).isContent) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                yes.setTextColor(Color.parseColor(if (s.isContent) "#ffffff" else "#172434"))
                no.background = resources.getDrawable(if (s.isContent) R.drawable.background_rectangle_light_black_white_content else R.drawable.background_rectangle_light_black_black_content)
                no.setTextColor(Color.parseColor(if (s.isContent) "#172434" else "#ffffff"))


                val e = v.findViewById<EditText>(R.id.survey_title_ox)
                e.setText(s.getTitle())
                e.keyListener = null

            }


        }


    }

}
