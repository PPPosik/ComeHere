package com.example.khj_pc.gaonnuri.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.khj_pc.gaonnuri.Data.Chat
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.item_chat.view.*

import java.util.ArrayList

class ChatRecyclerViewAdapter(private val context: Context, var chats: ArrayList<Chat>) : RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatRecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewAdapter.ChatRecyclerViewViewHolder, position: Int) {
        val chat = chats[position]
        val name : String = SharedPreferenceUtil.getPreference(context, "name")!!
        holder.root.setBackgroundColor(Color.parseColor(if (chat.userName == name) "#f5f9ff" else "#ffffff"))
        holder.root.item_chat_username.text = chat.userName
        holder.root.item_chat_time.text = chat.time
        holder.root.item_chat_content.text = chat.content
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    fun addData(chat : Chat) {
        chats.add(chat)
        notifyDataSetChanged()
    }

    class ChatRecyclerViewViewHolder(internal var root: View) : RecyclerView.ViewHolder(root)

}
