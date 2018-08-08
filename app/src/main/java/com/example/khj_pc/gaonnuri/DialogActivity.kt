package com.example.khj_pc.gaonnuri

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.khj_pc.gaonnuri.Dialog.CustomDialog
import kotlinx.android.synthetic.main.activity_dialog.*

public class DialogActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        dialogButtonListener()
    }

    fun dialogButtonListener(){
        dialog_button.setOnClickListener {
            val customDialog : CustomDialog = CustomDialog(this)
            customDialog.callFunction()
        }
    }
}