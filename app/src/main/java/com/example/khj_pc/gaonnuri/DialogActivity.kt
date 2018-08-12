package com.example.khj_pc.gaonnuri

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.example.khj_pc.gaonnuri.Adapter.ViewPageAdapter
import com.example.khj_pc.gaonnuri.Dialog.CustomDialog
import kotlinx.android.synthetic.main.activity_dialog.*

public class DialogActivity: AppCompatActivity(){

    val testData : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        testDataAdd()
        viewpager.adapter = ViewPageAdapter(testData, this)
        tablayout.setupWithViewPager(viewpager, true)

    }

    fun testDataAdd(){
        testData.add("AAA")
        testData.add("AAA")
        testData.add("AAA")
        testData.add("AAA")
    }

}