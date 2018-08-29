package com.example.khj_pc.gaonnuri

import android.app.AlertDialog
import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.khj_pc.gaonnuri.Data.*
import kotlinx.android.synthetic.main.content_survey_create.*
import kotlinx.android.synthetic.main.item_survey_multiple.view.*
import kotlinx.android.synthetic.main.item_survey_ox.view.*
import kotlinx.android.synthetic.main.item_survey_text.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurveyCreateActivity : Activity() {

    lateinit var surveys: ArrayList<Survey>

    lateinit var viewmodels : ArrayList<SurveyViewModel>
    private var linearLayout: LinearLayout? = null

    lateinit var roomid : String

    companion object {
        val TAG : String = SurveyCreateActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_create)
        surveys = ArrayList()
        viewmodels = ArrayList()

        linearLayout = findViewById(R.id.survey_create_linear_layout)
        roomid = intent.getStringExtra("id")

        val vv = findViewById<View>(R.id.survey_create_add) ?: return
        vv.setOnClickListener {
            val info = arrayOf<CharSequence>("다지선다", "O/X", "주관식")


            val builder = AlertDialog.Builder(this@SurveyCreateActivity)

            builder.setTitle("어떤 설문을 만드시겠습니까?")

            builder.setItems(info) { dialog, which ->
                addSurvey(which)
                dialog.dismiss()
                listTest()
            }

            builder.show()
        }

        setListeners()
    }

    fun addSurvey(type: Int) {
        val layout = intArrayOf(R.layout.item_survey_multiple, R.layout.item_survey_ox, R.layout.item_survey_text)
        var customView : View? = null
        customView = LayoutInflater.from(this).inflate(layout[type], linearLayout, false)
        linearLayout!!.addView(customView)

        when (type) {
            0 -> {
                //surveys.add(Survey.SurveyMultiple())
                var surveyCell : SurveyCell = SurveyCell(0, "", ArrayList(), "")
                var viewModel : SurveyViewModel = SurveyViewModel(customView, surveyCell)
                viewmodels.add(viewModel)
            }
            1 -> {
                //surveys.add(Survey.SurveyOX())
                var surveyCell : SurveyCell = SurveyCell(1, "", ArrayList(), "")
                var viewModel : SurveyViewModel = SurveyViewModel(customView, surveyCell)
                viewmodels.add(viewModel)
            }
            2 -> {
                //surveys.add(Survey.SurveyText())
                var surveyCell : SurveyCell = SurveyCell(2, "", ArrayList(),"")
                var viewModel : SurveyViewModel = SurveyViewModel(customView, surveyCell)
                viewmodels.add(viewModel)

            }
        }
        //        View v = LayoutInflater.from(this).inflate(layout[type], null);

    }

    fun listTest() {
        Log.d("size", viewmodels.size.toString())
        var count = 0
        for(i in 0 until viewmodels.size) {
            count++
            Log.d("count", "count = ${count}")
            when(viewmodels[i].cell.type) {
                0 -> {
                    viewmodels[i].view.survey_title_multiple.hint = "성공"
                }
                1 -> {
                    viewmodels[i].view.survey_title_ox.setHint("성공2")
                }
                2 -> {
                    viewmodels[i].view.survey_title_text.setHint("성공3")
                }
            }
        }
    }

    fun setListeners() {
        finishButton.setOnClickListener {
            sendSurvey()
        }
    }

    fun sendSurvey() {
        val surveyService : SurveyService = RetrofitUtil.getLoginRetrofit(applicationContext).create(SurveyService::class.java)
        for(i in 0 until viewmodels.size) {
            when(viewmodels[i].cell.type) {
                0 -> {
                    viewmodels[i].cell.title = viewmodels[i].view.survey_title_multiple.text.toString()
                    viewmodels[i].cell.body.add(SurveyBody(viewmodels[i].view.survey_multiple_content1.text.toString(), 0))
                    viewmodels[i].cell.body.add(SurveyBody(viewmodels[i].view.survey_multiple_content2.text.toString(), 0))
                    viewmodels[i].cell.body.add(SurveyBody(viewmodels[i].view.survey_multiple_content3.text.toString(), 0))
                    viewmodels[i].cell.body.add(SurveyBody(viewmodels[i].view.survey_multiple_content4.text.toString(), 0))
                    viewmodels[i].cell.writeString = ""
                }
                1 -> {
                    viewmodels[i].cell.title = viewmodels[i].view.survey_title_ox.text.toString()
                    viewmodels[i].cell.body.add(SurveyBody("그렇다", 0))
                    viewmodels[i].cell.body.add(SurveyBody("아니다", 0))
                    viewmodels[i].cell.writeString = ""
                }
                2 -> {
                    viewmodels[i].cell.title = viewmodels[i].view.survey_title_text.toString()
                    viewmodels[i].cell.writeString = ""
                }
            }
        }

        val surveyData : SurveyData = SurveyData(roomid, ArrayList(viewmodels.map { it.cell }) )
        val call : Call<SurveyResponse> = surveyService.createSurvey(surveyData)
        call.enqueue(object : Callback<SurveyResponse> {
            override fun onFailure(call: Call<SurveyResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<SurveyResponse>, response: Response<SurveyResponse>) {
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            Log.d(TAG, response.body()!!.message)
                            toast("설문 작성에 성공하였습니다")
                            finish()
                        }
                        else -> {
                            Log.e(TAG, "error code is ${response.code()}")
                            toast("네트워크 에러가 발생하였습니다!")
                        }
                    }
                }
            }

        })
    }
}
