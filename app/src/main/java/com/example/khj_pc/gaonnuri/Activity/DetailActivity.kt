package com.example.khj_pc.gaonnuri.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import com.example.khj_pc.gaonnuri.Adapter.BoardRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Adapter.DetailViewPageAdapter
import com.example.khj_pc.gaonnuri.Data.Board
import com.example.khj_pc.gaonnuri.Listener.DetailNavigationListener
import com.example.khj_pc.gaonnuri.Data.SingleRoomResult
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.activity_detail.*
import com.example.khj_pc.gaonnuri.Service.PostService
import com.example.khj_pc.gaonnuri.Service.RoomService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.app_bar_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_board.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private var boards : ArrayList<Board> = ArrayList()
    private var imgUrl : ArrayList<String> = ArrayList()

    lateinit var id : String
    lateinit var room : SingleRoomResult

    companion object {
        val TAG : String = DetailActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.title = null

        id = intent.getStringExtra("id")
        setNavigationDrawer()
        setListeners()
        setBoardDummyData()
        loadTopThree()
        loadData()

    }

    fun setNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, detail_drawerLayout, detail_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        detail_drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationListener : DetailNavigationListener = DetailNavigationListener(this, detail_drawerLayout)
        navigationListener.id = this.id
        detail_nav_view.setNavigationItemSelectedListener(navigationListener)
        detail_nav_view.getHeaderView(0).name.text = SharedPreferenceUtil.getPreference(applicationContext, "name")!!
        detail_nav_view.getHeaderView(0).idText.text = SharedPreferenceUtil.getPreference(applicationContext, "username")

    }

    fun setBoardDummyData() {
        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))
        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))
        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))

        for (b in boards) {
            for (i in 0..2)
                b.content = b.content + "\n" + b.content
        }
    }

    fun loadTopThree() {
        var postService : PostService = RetrofitUtil.getLoginRetrofit(applicationContext).create(PostService::class.java)
        var call : Call<ArrayList<Board>> = postService.getTopThree(id)
        call.enqueue(object : Callback<ArrayList<Board>> {
            override fun onFailure(call: Call<ArrayList<Board>>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Board>>?, response: Response<ArrayList<Board>>?) {
                if(response!!.body() != null) {
                    when(response.code()) {
                        200 -> {
                            boards = response.body()!!
                            setRecyclerView()
                        }
                        else -> {
                            Log.e(TAG, "error code : ${response.code()}")
                        }
                    }
                }
            }

        } )
    }

    fun setListeners() {
        home_button.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun setDetailViewPager(){
        for (i in 0 until imgUrl.size) {
            var s : String = imgUrl[i]
            var url : String = ""
            if(s.contains("http") || s.contains("HTTP")) {
                url = s
            } else {
                url = "http://13.125.103.237:23002/images/$s"
            }
            imgUrl.set(i, url)
        }
        detail_viewpager.adapter = DetailViewPageAdapter(imgUrl, this)
        detail_tablayout.setupWithViewPager(detail_viewpager, true)
    }

    fun setRecyclerView() {
        val adapter = BoardRecyclerViewAdapter(this, boards)
        board_recyclerview.adapter = adapter
        board_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (detail_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            detail_drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun loadData() {
        var roomService : RoomService = RetrofitUtil.getLoginRetrofit(applicationContext).create(RoomService::class.java)
        var call : Call<SingleRoomResult> = roomService.getSingleRoomInfo(id)
        call.enqueue(object : Callback<SingleRoomResult> {
            override fun onFailure(call: Call<SingleRoomResult>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<SingleRoomResult>, response: Response<SingleRoomResult>) {
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            room = response.body()!!
                            imgUrl = ArrayList(room.images)

                            if(room.questionName.length > 7){
                                questName.textSize = 16f
                            }
                            else{
                                questName.textSize = 32f
                            }
                            questName.text = room.questionName

                            setDetailViewPager()
                        }

                        401 -> {
                            toast("해당하는 id가 없습니다")
                        }

                        500 -> {
                            toast("Server Error!")
                        }
                    }
                }
            }

        })
    }
}