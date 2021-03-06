package com.example.khj_pc.gaonnuri.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.khj_pc.gaonnuri.Adapter.RecyclerViewItemAdapter
import com.example.khj_pc.gaonnuri.Data.Room
import com.example.khj_pc.gaonnuri.Data.UserResult
import com.example.khj_pc.gaonnuri.Listener.MainNavigationListener
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.UserService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val testData: ArrayList<String> = ArrayList()
    lateinit var dataList: List<Room>

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        private val FINISH_INTERVAL_TIME: Long = 2000
        private var backPressedTime: Long = 0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(MainNavigationListener(this, drawer_layout))
        nav_view.getHeaderView(0).name.text = SharedPreferenceUtil.getPreference(applicationContext, "name")!!
        nav_view.getHeaderView(0).idText.text = SharedPreferenceUtil.getPreference(applicationContext, "username")
        loadData()


//        var intent: Intent = Intent(this, BoardActivity::class.java)
//        startActivity(intent)

    }

    fun setRecyclerView() {
        if(dataList.isNotEmpty()) {
            Log.d("type", "type is ${dataList[0].type}")
            competitionRecyclerView.layoutManager = GridLayoutManager(this, 3)
            competitionRecyclerView.adapter = RecyclerViewItemAdapter(dataList, this)
            competitionRecyclerView.isNestedScrollingEnabled = false

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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime

            if (intervalTime in 0..FINISH_INTERVAL_TIME) {
                super.onBackPressed()
                finish()
            } else {
                backPressedTime = tempTime
                Toast.makeText(applicationContext, "뒤로 버튼을 한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun finish() {
        super.finish()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity()
        }
    }
}
