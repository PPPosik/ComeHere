package com.example.khj_pc.gaonnuri

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View

import com.example.khj_pc.gaonnuri.Adapter.SearchRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Data.Contest
import com.example.khj_pc.gaonnuri.Data.Room
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*
import kotlinx.android.synthetic.main.content_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchActivity : AppCompatActivity() {
    private var rooms: ArrayList<Room>? = null
    private var adapter: SearchRecyclerViewAdapter? = null

    companion object {
        val TAG : String = SearchActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        rooms = ArrayList()

        search_back.setOnClickListener {
            finish()
        }

        loadData()

    }

    fun setEditText() {
        searchEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                empty_view.visibility = View.GONE
                search_recyclerview.visibility = View.VISIBLE
                adapter!!.filter(searchEditText.text.toString().toLowerCase(Locale.getDefault()))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    fun setRecyclerView(){
        adapter = SearchRecyclerViewAdapter(this, rooms!!)
        search_recyclerview.layoutManager = LinearLayoutManager(this)
        search_recyclerview.adapter = adapter

        search_recyclerview.visibility = View.GONE
        empty_view.visibility = View.VISIBLE

        setEditText()
    }

    fun loadData() {
        var roomService : RoomService = RetrofitUtil.getLoginRetrofit(applicationContext).create(RoomService::class.java)
        var call : Call<List<Room>> = roomService.getAllRoomData()
        call.enqueue(object: Callback<List<Room>> {
            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                if(response != null && response.isSuccessful) {
                    Log.d("code", response.code().toString())
                    when(response.code()) {
                        200 -> {
                            rooms = ArrayList(response.body())
                            Log.d(TAG, "Size = " + rooms!!.size.toString())
                            setRecyclerView()
                        }
                    }
                }
            }

        })
    }

}
