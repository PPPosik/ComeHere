package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import com.example.khj_pc.gaonnuri.Adapter.RecyclerViewItemAdapter
import com.example.khj_pc.gaonnuri.Data.Room
import com.example.khj_pc.gaonnuri.Data.UserResult
import com.example.khj_pc.gaonnuri.Listener.MainNavigationListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val testData: ArrayList<String> = ArrayList()
    lateinit var dataList: List<Room>

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var intent: Intent = Intent(this, SurveyDoActivity::class.java)
        startActivity(intent)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(MainNavigationListener(this, drawer_layout))

        loadData()
    }

    fun setRecyclerView() {
        competitionRecyclerView.layoutManager = GridLayoutManager(this, 3)
        competitionRecyclerView.adapter = RecyclerViewItemAdapter(dataList, this)
        competitionRecyclerView.isNestedScrollingEnabled = false

        seminarRecyclerView.layoutManager = GridLayoutManager(this, 3)
        seminarRecyclerView.adapter = RecyclerViewItemAdapter(dataList, this)
        seminarRecyclerView.isNestedScrollingEnabled = false
    }

    fun testDataAdd() {
        testData.add("AAA")
        testData.add("BBB")
        testData.add("CCC")
        testData.add("DDD")
        testData.add("EEE")
        testData.add("FFF")
        testData.add("GGG")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun loadData() {
        val userName: String? = SharedPreferenceUtil.getPreference(applicationContext, "username")
        Log.d("asdf", userName)
        val userService: UserService = RetrofitUtil.getLoginRetrofit(applicationContext).create(UserService::class.java)
        val call: Call<UserResult> = userService.getUserInfo(userName!!)
        call.enqueue(object : Callback<UserResult> {
            override fun onFailure(call: Call<UserResult>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<UserResult>?, response: Response<UserResult>?) {
                Log.e("test", response!!.code().toString())
                if (response != null && response.isSuccessful) {
                    when (response.code()) {
                        200 -> {
                            toast("성공적으로 데이터가 로딩되었습니다.")
                            dataList = response.body()!!.room_string
                            Log.d("size", dataList.size.toString())
                            setRecyclerView()
                        }
                        401 -> {
                            toast("로그인 정보가 일치하지 않습니다")
                        }
                    }
                }
            }

        })
    }
}
