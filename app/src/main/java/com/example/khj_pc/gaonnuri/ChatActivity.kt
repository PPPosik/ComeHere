package com.example.khj_pc.gaonnuri

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_chat.*
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class ChatActivity : AppCompatActivity() {

    private var chats: ArrayList<Chat>? = null
    private lateinit var adapter : ChatRecyclerViewAdapter
    lateinit var mSocket : Socket
    lateinit var roomId : String

    companion object {
        val TAG : String = ChatActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.elevation = 0.0f

        roomId = intent.getStringExtra("id")
        Log.d("roomid", roomId)

        mSocket = IO.socket("http://ssumon.com:23002")
        mSocket.connect()

        setSocket()
        setListeners()

        chat_back.setOnClickListener {
            finish()
        }
    }

    fun setRecyclerView() {
        adapter = ChatRecyclerViewAdapter(this, chats!!)
        chat_recyclerview.adapter = adapter
        chat_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()

        chat_recyclerview.scrollToPosition(adapter.itemCount - 1)
    }

    fun setSocket() {
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("init_data", onInitData)
        mSocket.on("receive_message", receiveMessage)
    }

    @SuppressLint("SimpleDateFormat")
    fun setListeners() {
        chat_send.setOnClickListener {
            if(chat_content.text.toString().isNotEmpty()) {
                val data : JSONObject = JSONObject()
                val name : String = SharedPreferenceUtil.getPreference(applicationContext, "name")!!
                val id : String = SharedPreferenceUtil.getPreference(applicationContext, "username")!!

                val format = SimpleDateFormat("EEE MMM dd yyyy '00:00:00'  zZ")
                val date : Date = Date()
                var chat : Chat = Chat(name, format.format(date), chat_content.text.toString())
                adapter.addData(chat)

                data.put("name", name)
                data.put("id", id)
                data.put("message", chat_content.text.toString())
                data.put("room_object_id", roomId)
                mSocket.emit("send_message", data)

                chat_content.setText("")

                chat_recyclerview.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    override fun finish() {
        val data = JSONObject()
        val id : String = SharedPreferenceUtil.getPreference(applicationContext, "username")!!
        data.put("room_object_id", roomId)
        data.put("id", id)
        mSocket.emit("leaveRoom", data)
        super.finish()
    }
    val onConnect : Emitter.Listener = Emitter.Listener {
        Log.d(TAG, "connected")
        val data = JSONObject()
        val id : String = SharedPreferenceUtil.getPreference(applicationContext, "username")!!
        data.put("room_object_id", roomId)
        data.put("id", id)
        mSocket.emit("joinRoom", data)
    }

    val onInitData : Emitter.Listener = Emitter.Listener {args ->
        runOnUiThread {
            var data : JSONObject = args[0] as JSONObject
            Log.d("data2", data.toString())
            data = data.getJSONObject("result")
            val messages : JSONArray = data.getJSONArray("messages")
            val gson : Gson = Gson()
            chats = ArrayList(gson.fromJson(messages.toString() , Array<Chat>::class.java).toList())
            Log.d(TAG, chats!!.size.toString())
            setRecyclerView()
        }

    }

    val receiveMessage : Emitter.Listener = Emitter.Listener {args ->
        runOnUiThread {
            var data : JSONObject = args[0] as JSONObject
            var chat : Chat = Chat(data.getString("author"), data.getString("created_at"), data.getString("message"))
            adapter.addData(chat)
            chat_recyclerview.scrollToPosition(adapter.itemCount - 1)
            Log.e("receiveData", data.toString())
        }
    }
}
