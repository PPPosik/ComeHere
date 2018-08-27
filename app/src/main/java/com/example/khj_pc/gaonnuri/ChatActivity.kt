package com.example.khj_pc.gaonnuri

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import com.example.khj_pc.gaonnuri.Adapter.ChatRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Data.Chat
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.content_chat.*
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject



class ChatActivity : AppCompatActivity() {

    private var chats: ArrayList<Chat>? = null
    lateinit var mSocket : Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.elevation = 0.0f

        mSocket = IO.socket("http://ssumon.com:23002")
        mSocket.connect()

        setSocket()
    }

    companion object {
        val TAG : String = ChatActivity::class.java.simpleName
    }

    fun setRecyclerView() {
        val adapter = ChatRecyclerViewAdapter(this, chats!!)
        chat_recyclerview.adapter = adapter
        chat_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    fun setSocket() {
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("init_data", onInitData)
    }


    val onConnect : Emitter.Listener = Emitter.Listener {
        val data = JSONObject()
        try {
            Log.d(TAG, "connect")
            data.put("room_object_id", "5b6a5df2dc111638ab02b864")
            data.put("id", "purplebeen@daum.net")
            mSocket.emit("joinRoom", data)
        } catch(e : JSONException){

        }
    }

    val onInitData : Emitter.Listener = Emitter.Listener {args ->
        runOnUiThread {
            var data : JSONObject = args[0] as JSONObject
            Log.d("data", data.toString())
            data = data.getJSONObject("result")

            val messages : JSONArray = data.getJSONArray("messages")
            Log.d(TAG, "message : "  + messages[0].toString())

            val gson : Gson = Gson()
            chats = ArrayList(gson.fromJson(messages.toString() , Array<Chat>::class.java).toList())
            Log.d("asdf", chats!![0].content)

            Log.d(TAG, "size = " + chats!![0].content)

            setRecyclerView()
        }

    }
}
