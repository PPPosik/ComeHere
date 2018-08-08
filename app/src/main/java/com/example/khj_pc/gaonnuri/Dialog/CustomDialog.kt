package com.example.khj_pc.gaonnuri.Dialog

import android.content.Context
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.khj_pc.gaonnuri.R
import kotlinx.android.synthetic.main.custom_dialog.*

public class CustomDialog(context : Context) {
    val dialog = Dialog(context)

    fun callFunction() : Unit{
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()

        dialog.yes_button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.no_button.setOnClickListener{
            dialog.dismiss()
        }
    }
}