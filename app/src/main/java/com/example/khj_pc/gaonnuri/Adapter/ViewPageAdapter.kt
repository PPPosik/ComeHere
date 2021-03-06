package com.example.khj_pc.gaonnuri.Adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import com.example.khj_pc.gaonnuri.Data.Room
import com.example.khj_pc.gaonnuri.Dialog.CustomDialog
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Util.loadUrl
import com.example.khj_pc.gaonnuri.Util.loadUrlWithCenterCrop
import kotlinx.android.synthetic.main.pager_adapter.view.*

class ViewPageAdapter(val room: Room, context: Context) : PagerAdapter() {
    var inflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var v: View? = null
        v = inflater.inflate(R.layout.pager_adapter, container, false)
        v.dialog_button.setOnClickListener {
            val customDialog = CustomDialog(container.context, room._id)
            customDialog.callFunction()
        }
        var url : String = ""
        if(room.images[position].contains("http") || room.images[position].contains("HTTP")) {
            url = room.images[position].toLowerCase()
        } else {
            url = "http://\"http://13.125.103.237:23002/images/${room.images[position]}"
        }
        v.imageView.loadUrlWithCenterCrop(url)
        v.contextText.text = Html.fromHtml(room.context)
        v.requestPersonId.text = room.requestPersonId
        v.questName.text = room.questionName

        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = room.images.size

}