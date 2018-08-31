package com.example.khj_pc.gaonnuri.Activity

import android.os.Bundle
import android.app.Activity
import android.support.percent.PercentLayoutHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.khj_pc.gaonnuri.Data.*

import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.SurveyService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class SurveyChartActivity : Activity() {
    private var linearLayout: LinearLayout? = null
    private lateinit var surveyList : ArrayList<SurveyResultCell>

    companion object {
        val TAG : String = SurveyChartActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_chart)

        linearLayout = findViewById(R.id.survey_create_linear_layout)

//        val results = ArrayList<SurveyResult>()
//        results.add(SurveyResult.Text("1. 본인의 이름은?(ex 홍길동)", "정승욱 정승욱1 정승욱_악마버전 정승욱_코딩충 정승욱_스"))
//        results.add(SurveyResult.OX("2. 다시 행사를 개최한다면 주말이었으면 좋겠다.", intArrayOf(78, 22)))
//        results.add(SurveyResult.Multiple("4. 식사중 가장 불만족 스러웠던 식사는?", arrayOf("1.아침", "2.점심", "3.저녁", "4.야식"), intArrayOf(78, 12, 6, 4)))

        loadData()
    }

    fun loadData() {
        val surveyService = RetrofitUtil.getLoginRetrofit(applicationContext).create(SurveyService::class.java)
        val call = surveyService.getSurvey(SurveyRoomID(intent.getStringExtra("id")))
        call.enqueue(object : Callback<SurveyResponseData>  {
            override fun onFailure(call: Call<SurveyResponseData>, t: Throwable) {
                toast("설문조사 정보가 없습니다")
                Log.e(TAG, t.toString())
                finish()
            }

            override fun onResponse(call: Call<SurveyResponseData>, response: Response<SurveyResponseData>) {
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            Log.d(TAG, "successful")
                            surveyList = response.body()!!.survey
                            setLayout()
                        }
                    }
                }
            }

        })
    }


    fun setLayout() {
        for (i in 0 until surveyList.size) {
            val cell = surveyList[i]
            if (cell.type == 0) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_chart_multiple, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)
                val linearLayout1 = v.findViewById<LinearLayout>(R.id.survey_content_multiple_chart)
                for (j in 0..3) {
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true)
                    val p = (((cell.body[j].check).toFloat() / getBodyCheckCount(cell.body)) * 100)
                    val vvv = linearLayout1.getChildAt(j)
                    (vvv.findViewById<View>(R.id.item_chart_keyword_text) as TextView).text = cell.body[j].context
                    (vvv.findViewById<View>(R.id.item_chart_keyword_value) as TextView).text = String.format("%.2f", p)
                    val vv = vvv.findViewById<View>(R.id.item_chart_keyword_bar)
                    (vv.layoutParams as PercentLayoutHelper.PercentLayoutParams).percentLayoutInfo.widthPercent = p / 100 * 2 / 3
                    //                    ((PercentLayoutHelper.PercentLayoutParams) vv.getLayoutParams()).getPercentLayoutInfo().widthPercent = ((float) k.getNum() / max) * 2 / 3;


                }
                val e = v.findViewById<EditText>(R.id.survey_title_multiple)
                e.setText(cell.title)
                e.keyListener = null

            } else if (cell.type == 1) {
                LayoutInflater.from(this).inflate(R.layout.item_survey_chart_ox, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)
                val linearLayout1 = v.findViewById<LinearLayout>(R.id.survey_content_ox_chart)
                val sum = getBodyCheckCount(cell.body)

                run {
                    val percent = (cell.body[0].check.toFloat() / sum) * 100
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true)
                    val vvv = linearLayout1.getChildAt(0)
                    (vvv.findViewById<View>(R.id.item_chart_keyword_text) as TextView).text = "그렇다"
                    (vvv.findViewById<View>(R.id.item_chart_keyword_value) as TextView).text = String.format("%.2f", percent)
                    val vv = vvv.findViewById<View>(R.id.item_chart_keyword_bar)
                    (vv.layoutParams as PercentLayoutHelper.PercentLayoutParams).percentLayoutInfo.widthPercent = percent / 100 * 2 / 3
                    Log.d("brfuck", "onCreate: " + percent * 2 / 3)
                }

                run {
                    val percent = (cell.body[1].check.toFloat() / sum) * 100
                    LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, linearLayout1, true)
                    val vvv = linearLayout1.getChildAt(1)
                    (vvv.findViewById<View>(R.id.item_chart_keyword_text) as TextView).text = "아니다"
                    (vvv.findViewById<View>(R.id.item_chart_keyword_value) as TextView).text = percent.toString()
                    val vv = vvv.findViewById<View>(R.id.item_chart_keyword_bar)
                    (vv.layoutParams as PercentLayoutHelper.PercentLayoutParams).percentLayoutInfo.widthPercent = percent / 100 * 2 / 3

                }
                val e = v.findViewById<EditText>(R.id.survey_title_ox)
                e.setText(cell.title)
                e.keyListener = null

            } else {
                LayoutInflater.from(this).inflate(R.layout.item_survey_text, linearLayout, true)
                val v = linearLayout!!.getChildAt(i)
                var e = v.findViewById<EditText>(R.id.content)
                e.setText(cell.writeString.toString())
                e.keyListener = null
                e = v.findViewById(R.id.survey_title_text)
                e.setText(cell.title)
                e.keyListener = null
            }
        }

    }

    fun getBodyCheckCount(bodyList : ArrayList<SurveyBody>) : Int {
        var sum : Int = 0
        for(body in bodyList) {
            sum += body.check
        }
        return sum
    }
}
