package com.example.khj_pc.gaonnuri.Dialog

import android.content.Context
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.khj_pc.gaonnuri.Data.Result
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.RetrofitUtil
import com.example.khj_pc.gaonnuri.RoomService
import com.example.khj_pc.gaonnuri.SharedPreferenceUtil
import kotlinx.android.synthetic.main.custom_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class CustomDialog(val context : Context, val id : String) {
    val dialog = Dialog(context, R.style.AppCompatAlertDialogStyle)

    companion object {
        val TAG : String = CustomDialog::class.java.simpleName
    }

    fun callFunction() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
//        dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()

        dialog.yes_button.setOnClickListener {
            enterRoom()
        }

        dialog.no_button.setOnClickListener{
            dialog.dismiss()
        }
    }

    private fun enterRoom() {
        var roomService : RoomService = RetrofitUtil.getLoginRetrofit(context).create(RoomService::class.java)
        var userid : String? = SharedPreferenceUtil.getPreference(context, "username")
        var call : Call<Result> = roomService.enter(userid!!, id)
        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.body() != null && response.isSuccessful) {
                    when(response.code()) {
                        200 -> {
                            Toast.makeText(context, "해당 행사에 성공적으로 입장하였습니다!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
                }
            }

        })

    }
}