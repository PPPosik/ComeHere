package com.example.khj_pc.gaonnuri.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import com.example.khj_pc.gaonnuri.Adapter.BoardRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Data.Board
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.PostService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import kotlinx.android.synthetic.main.activity_board.*
import java.util.ArrayList
import kotlinx.android.synthetic.main.content_board.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardActivity : AppCompatActivity() {


    private var boards: ArrayList<Board>? = null
    private var adapter: BoardRecyclerViewAdapter? = null

    lateinit var roomId : String

    companion object {
        val TAG : String = BoardActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        roomId = intent.getStringExtra("id")
        boards = ArrayList()

        loadData()
        setLissteners()
    }

    fun setRecyclerView() {
        adapter = BoardRecyclerViewAdapter(this, boards)
        board_recyclerview.layoutManager = LinearLayoutManager(this)
        board_recyclerview.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun setLissteners() {
        floatingActionButton.setOnClickListener {
            val intent: Intent = Intent(this, WriteActivity::class.java)
            intent.putExtra("roomId", roomId)
            startActivity(intent)
        }
    }

    fun loadData() {
        var postService : PostService = RetrofitUtil.getLoginRetrofit(applicationContext).create(PostService::class.java)
        var call : Call<ArrayList<Board>> = postService.getAllPostFromRoomId(roomId)
        call.enqueue(object : Callback<ArrayList<Board>> {
            override fun onFailure(call: Call<ArrayList<Board>>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Board>>?, response: Response<ArrayList<Board>>?) {
                if(response != null &&  response.isSuccessful) {
                    when(response.code()) {
                        200 -> {
                            boards = response.body()
                            setRecyclerView()
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
