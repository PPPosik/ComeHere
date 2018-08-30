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
import com.example.khj_pc.gaonnuri.Data.*

import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.SurveyService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import kotlinx.android.synthetic.main.content_survey_do.*
import kotlinx.android.synthetic.main.item_survey_multiple.*
import kotlinx.android.synthetic.main.item_survey_multiple.view.*
import kotlinx.android.synthetic.main.item_survey_ox.view.*
import kotlinx.android.synthetic.main.item_survey_text.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class SurveyDoActivity : Activity() {

    lateinit var surveys: ArrayList<Survey>
    private var linearLayout: LinearLayout? = null
    lateinit var roomId : String
    lateinit var surveyForm : ArrayList<SurveyCell>

    val viewModelList : ArrayList<SurveyViewModel> = ArrayList()

    companion object {
        val TAG : String = SurveyDoActivity::class.java.simpleName
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_do)

        roomId = intent.getStringExtra("id")


        surveys = ArrayList()
//        surveys.add(Survey.SurveyText("1. 본인의 이름은?(ex 홍길동)"))
//        surveys.add(Survey.SurveyOX("2. 다시 행사를 개최한다면 주말이었으면 좋겠다.", true))
//        surveys.add(Survey.SurveyOX("3. 행사 개최자는 잘생겼다.", false))
//        surveys.add(Survey.SurveyMultiple("4. 식사중 가장 불만족 스러웠던 식사는?", arrayOf("아침", "점심", "저녁", "야식1")))

        loadSurveyData()
    }

    fun loadSurveyData() {
        val surveyService = RetrofitUtil.getLoginRetrofit(applicationContext).create(SurveyService::class.java)
        val call = surveyService.getSurvey(SurveyRoomID(roomId))
        call.enqueue(object : Callback<SurveyResponseData> {
            override fun onFailure(call: Call<SurveyResponseData>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onResponse(call: Call<SurveyResponseData>, response: Response<SurveyResponseData>) {
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            surveyForm = response.body()!!.surveyForm
                            toast("설문조사 정보를 가져오는데 성공하였습니다!")
                            setLayout()
                        }

                        else -> {
                            Log.e(TAG, "http error code is ${response.code()}")
                        }
                    }
                }
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun setLayout() {
        for (i in 0 until surveyForm.size) {
            val cell = surveyForm[i]
            if (cell.type == 2) { //제목 / 답변만 있는 editText
                val view = LayoutInflater.from(this).inflate(R.layout.item_survey_text, linearLayout, false)
                view.survey_title_text.setText(cell.title)
                view.survey_title_text.keyListener = null
                viewModelList.add(SurveyViewModel(view, cell))
                survey_create_linear_layout.addView(view)

            } else if (cell.type == 0) { //4지선다형 설문
                val view = LayoutInflater.from(this).inflate(R.layout.item_survey_multiple, linearLayout, false)

                view.survey_multiple_content1.setText(cell.body[0].context)
                view.survey_multiple_content1.setBackgroundColor(Color.parseColor(if (cell.body[0].check == 1) "#F2F9FE" else "#ffffff"))
                view.survey_multiple_content1.setOnClickListener {
                    if(cell.body[0].check == 1) {
                        cell.body[0].check = 0
                    } else {
                        cell.body[0].check = 1
                    }
                    view.survey_multiple_content1.setBackgroundColor(Color.parseColor(if (cell.body[0].check == 1) "#F2F9FE" else "#ffffff"))
                }
                view.survey_multiple_content1.keyListener = null

//                view.surv = v.findViewById(R.id.survey_multiple_content2)
                view.survey_multiple_content2.setText(cell.body[1].context)
                view.survey_multiple_content2.setBackgroundColor(Color.parseColor(if (cell.body[0].check == 1) "#F2F9FE" else "#ffffff"))
                view.survey_multiple_content2.setOnClickListener {
                    if(cell.body[1].check == 1) {
                        cell.body[1].check = 0
                    } else {
                        cell.body[1].check = 1
                    }
                    view.survey_multiple_content2.setBackgroundColor(Color.parseColor(if (cell.body[1].check == 1) "#F2F9FE" else "#ffffff"))
                }
                view.survey_multiple_content2.keyListener = null

//                ee = v.findViewById(R.id.survey_multiple_content3)
                view.survey_multiple_content3.setText(cell.body[2].context)
                view.survey_multiple_content3.setBackgroundColor(Color.parseColor(if (cell.body[2].check == 1) "#F2F9FE" else "#ffffff"))
                view.survey_multiple_content3.setOnClickListener {
                    if(cell.body[2].check == 1) {
                        cell.body[2].check = 0
                    } else {
                        cell.body[2].check = 1
                    }
                    view.survey_multiple_content3.setBackgroundColor(Color.parseColor(if (cell.body[2].check == 1) "#F2F9FE" else "#ffffff"))
                }
                view.survey_multiple_content3.keyListener = null

//                ee = v.findViewById(R.id.survey_multiple_content4)
                view.survey_multiple_content4.setText(cell.body[3].context)
                view.survey_multiple_content4.setBackgroundColor(Color.parseColor(if (cell.body[3].check == 1) "#F2F9FE" else "#ffffff"))
                view.survey_multiple_content4.setOnClickListener {
                    if(cell.body[2].check == 1) {
                        cell.body[2].check = 0
                    } else {
                        cell.body[2].check = 1
                    }
                    view.survey_multiple_content4.setBackgroundColor(Color.parseColor(if (cell.body[3].check == 1) "#F2F9FE" else "#ffffff"))
                }
                view.survey_multiple_content4.keyListener = null
                //TODO

//                val e = v.findViewById<EditText>(R.id.survey_title_multiple)
                view.survey_title_multiple.setText(cell.title)
                view.survey_title_multiple.keyListener = null

                viewModelList.add(SurveyViewModel(view, cell))
                survey_create_linear_layout.addView(view)

            } else { // O X Survey
                val view = LayoutInflater.from(this).inflate(R.layout.item_survey_ox, linearLayout, false)

                //val yes = v.findViewById<TextView>(R.id.survey_create_yes)
                //val no = v.findViewById<TextView>(R.id.survey_create_no)
                view.survey_create_yes.setOnClickListener {
                    cell.body[0].check = 1
                    cell.body[1].check = 0
                    view.survey_create_yes.background = resources.getDrawable(if (cell.body[0].check == 1) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                    view.survey_create_yes.setTextColor(Color.parseColor(if (cell.body[0].check == 1) "#ffffff" else "#172434"))
                    view.survey_create_no.background = resources.getDrawable(if (cell.body[1].check == 0) R.drawable.background_rectangle_light_black_white_content else R.drawable.background_rectangle_light_black_black_content)
                    view.survey_create_no.setTextColor(Color.parseColor(if (cell.body[1].check == 0) "#172434" else "#ffffff"))
                }
                view.survey_create_no.setOnClickListener {
                    cell.body[0].check = 0
                    cell.body[1].check = 1

                    Log.d("asdf", "on no click, check status is ${cell.body[1].check}")
                    Log.d("asdf", "on no click, check status is ${cell.body[0].check}")


                    view.survey_create_yes.background = resources.getDrawable(if (cell.body[0].check == 1) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                    view.survey_create_yes.setTextColor(Color.parseColor(if (cell.body[0].check == 1) "#ffffff" else "#172434"))
                    view.survey_create_no.background = resources.getDrawable(if (cell.body[1].check == 1 )R.drawable.background_rectangle_light_black_black_content else  R.drawable.background_rectangle_light_black_white_content)
                    view.survey_create_no.setTextColor(Color.parseColor(if (cell.body[1].check == 1) "#ffffff" else "#172434"))
                }

                view.survey_create_yes.background = resources.getDrawable(if (cell.body[0].check == 1) R.drawable.background_rectangle_light_black_black_content else R.drawable.background_rectangle_light_black_white_content)
                view.survey_create_yes.setTextColor(Color.parseColor(if (cell.body[0].check == 1) "#ffffff" else "#172434"))
                view.survey_create_no.background = resources.getDrawable(if (cell.body[1].check == 1) R.drawable.background_rectangle_light_black_white_content else R.drawable.background_rectangle_light_black_black_content)
                view.survey_create_no.setTextColor(Color.parseColor(if (cell.body[1].check == 1) "#172434" else "#ffffff"))

                view.survey_title_ox.setText(cell.title)
                view.survey_title_ox.keyListener = null

                viewModelList.add(SurveyViewModel(view, cell))
                survey_create_linear_layout.addView(view)

            }


        }

    }
}
