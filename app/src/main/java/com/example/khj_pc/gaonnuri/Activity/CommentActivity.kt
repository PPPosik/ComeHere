package com.example.khj_pc.gaonnuri.Activity

import android.os.Bundle
import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast

import com.example.khj_pc.gaonnuri.Adapter.ChatRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Data.Board
import com.example.khj_pc.gaonnuri.Data.Chat
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.PostService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.content_comment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class CommentActivity : Activity() {

    private var board: Board? = null
    private var comments: ArrayList<Chat>? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter : ChatRecyclerViewAdapter

    companion object {
        val TAG : String = CommentActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)


        val intent = intent
        board = intent.getSerializableExtra("board") as Board

        if (board == null) {
            Toast.makeText(this, "문제발생", Toast.LENGTH_SHORT).show()
            return
        }

        comments = board!!.comments
        setListeners()
        setRecyclerView()

        comment_back.setOnClickListener {
            finish()
        }
    }

    fun setRecyclerView() {
        adapter = ChatRecyclerViewAdapter(this, comments!!)
        comment_recyclerview.adapter = adapter
        comment_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    fun setListeners() {
        comment_send.setOnClickListener {
            addComment()
        }
    }

    fun addComment() {
        var postService : PostService = RetrofitUtil.getLoginRetrofit(applicationContext).create(PostService::class.java)
        val username : String = SharedPreferenceUtil.getPreference(applicationContext, "name")!!
        val id : String = SharedPreferenceUtil.getPreference(applicationContext, "username")!!
        var comment : Chat = Chat(username, "", comment_content.text.toString())
        comment.roomId = board!!.roomId
        comment.postId = board!!.id
        var call : Call<Board> = postService.addComment(comment)
        call.enqueue(object : Callback<Board> {
            override fun onFailure(call: Call<Board>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            comments = response.body()!!.comments
                            adapter.chats = comments!!
                            adapter.notifyDataSetChanged()
                            comment_content.setText("")
                        }
                    }
                }
            }

        })
    }
}
