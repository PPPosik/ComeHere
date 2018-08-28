package com.example.khj_pc.gaonnuri

import android.os.Bundle
import android.app.Activity
import android.support.percent.PercentLayoutHelper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.example.khj_pc.gaonnuri.Adapter.ChartRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Data.*
import kotlinx.android.synthetic.main.content_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartActivity : Activity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: ChartRecyclerViewAdapter? = null
    private var datas: ArrayList<CircleChart>? = null
    private var linearLayout: LinearLayout? = null
    private var keywordCharts: ArrayList<KeywordChart>? = null

    private lateinit var defaultList : ArrayList<Keyword>
    private lateinit var mlData: MLData

    companion object {
        val TAG : String = ChartActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        linearLayout = findViewById(R.id.keyword_chart_linearlayout)
        recyclerView = findViewById(R.id.chart_recyclerview)
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })

        loadData()

    }

    fun loadData() {
        val machineLearningService : MachineLearningService = RetrofitUtil.mlRetrofit.create(MachineLearningService::class.java)
        val call : Call<MLData> = machineLearningService.getMLData(RoomID(intent.getStringExtra("id")))
        call.enqueue(object : Callback<MLData> {
            override fun onFailure(call: Call<MLData>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<MLData>, response: Response<MLData>) {
                if(response.body() != null) {
                    when(response.code())  {
                        200 -> {
                            mlData = response.body()!!
                            defaultList = response.body()!!.keyword
                            setData()
                        }
                    }
                }
            }

        })
    }

    fun setData() {
        datas = ArrayList()

        val bathroom = ((mlData.topic_result.bathroom.toDouble() / mlData.topic_result.total.toDouble()) * 100).toInt()
        val enter = ((mlData.topic_result.enter.toDouble() / mlData.topic_result.total.toDouble()) * 100).toInt()
        val lost = ((mlData.topic_result.lost.toDouble() / mlData.topic_result.total.toDouble()) * 100).toInt()
        val common = ((mlData.topic_result.common.toDouble() / mlData.topic_result.total.toDouble()) * 100).toInt()

        Log.d("percent", "bathroom = ${bathroom}, enter = ${enter}, lost = ${lost}")
        if(bathroom > 15) {
            val add = datas!!.add(CircleChart("bathroom", "아 화장실이 이게 뭐야", bathroom))
            judgement.text = "화장실에 대한 불만이 많네요"
            if (enter > 15) {
                datas!!.add(CircleChart("enter", "아 진짜 행사 참여 할 맛 떨어지게 하네", enter))
                judgement.text = "입장에 대한 불만이 많네요"
                if (lost > 15) {
                    datas!!.add(CircleChart("lost", "내 충전기 어딧냐", lost))
                    judgement.text = "분실물에 대한 불만이 많네요"
                }
            }
        } else {
            datas!!.add(CircleChart("common", "무난하네요", common))
            judgement.text  = "전반적으로 무난하네요"
        }



        adapter = ChartRecyclerViewAdapter(this, datas!!)
        recyclerView!!.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        adapter!!.notifyDataSetChanged()

        keywordCharts = ArrayList()
        for(keyword in mlData.keyword) {
            keywordCharts!!.add(KeywordChart(keyword.key, keyword.value))
        }
        var max = -1
        for (k in keywordCharts!!)
            max = Math.max(k.num, max)


        for (k in keywordCharts!!) {
            val v = LayoutInflater.from(this).inflate(R.layout.item_chart_keyword, null)
            (v.findViewById<View>(R.id.item_chart_keyword_text) as TextView).text = k.keyword
            (v.findViewById<View>(R.id.item_chart_keyword_value) as TextView).text = k.num.toString()
            val vv = v.findViewById<View>(R.id.item_chart_keyword_bar)
            (vv.layoutParams as PercentLayoutHelper.PercentLayoutParams).percentLayoutInfo.widthPercent = k.num.toFloat() / max * 2 / 3
            linearLayout!!.addView(v)
        }

        findViewById<View>(R.id.chart_left).setOnClickListener {
            if (adapter!!.Position > 0)
                recyclerView!!.smoothScrollToPosition(--adapter!!.Position)
        }
        findViewById<View>(R.id.chart_right).setOnClickListener {
            if (adapter!!.Position < adapter!!.itemCount - 1) {
                recyclerView!!.smoothScrollToPosition(++adapter!!.Position)

            }
        }

    }

}
