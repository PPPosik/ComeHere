package com.example.khj_pc.gaonnuri.Adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.example.khj_pc.gaonnuri.Dialog.CustomDialog
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.pager_adapter.view.*

class ViewPageAdapter(val items : ArrayList<String>, context : Context) : PagerAdapter(){
    var inflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var v : View? = null

        if(position % 2 == 0){
            v = inflater.inflate(R.layout.pager_adapter, container, false)
            v.dialog_button.setOnClickListener {
                val customDialog = CustomDialog(container.context)
                customDialog.callFunction()
            }
        }
        else{
            v = inflater.inflate(R.layout.viewpage_text, container, false)
        }

        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = 4

}