package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.khj_pc.gaonnuri.Data.Result
import com.example.khj_pc.gaonnuri.Data.User
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private var registerIntent : Intent? = null
    private lateinit var hint_tmp : String
    private lateinit var date : String
    private lateinit var input : InputMethodManager

    companion object {
        val TAG : String = RegisterActivity::class.java.simpleName
    }

    private val editTextFocusListener = View.OnFocusChangeListener { view, hasFocus ->
        val edit = view as EditText

        if(hasFocus){
            hint_tmp = edit.hint.toString()
            edit.gravity = Gravity.START or Gravity.CENTER
            edit.hint = ""
            edit.textSize = (18 as Integer).toFloat()

            input = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            input.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
            // input.hideSoftInputFromWindow(edit.windowToken, 0)
        }
        else{
            if(edit.text.isEmpty()) {
                edit.gravity = Gravity.END or Gravity.CENTER
                edit.textSize = (14 as Integer).toFloat()
            }
            edit.hint = hint_tmp
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setListeners()
    }

    fun setListeners() {
        register_name.onFocusChangeListener = editTextFocusListener
        register_email.onFocusChangeListener = editTextFocusListener
        register_address.onFocusChangeListener = editTextFocusListener
        register_password.onFocusChangeListener = editTextFocusListener

        register_back.setOnClickListener {
            onBackPressed()
        }

        register_ok_button.setOnClickListener {
            register()
        }

        register_birthday.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    this@RegisterActivity,
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            )
            dpd.show(fragmentManager, "생년월일을 선택하세요")

        }
    }

    fun register() {
        var user : User = User(register_email.text.toString(), register_name.text.toString(), register_password.text.toString())
        user.birth = register_birthday.text.toString()
        user.address = register_address.text.toString()
        var userService : UserService = RetrofitUtil.retrofit.create(UserService::class.java)
        var call : Call<Result> = userService.register(user)
        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                if(response != null) {
                    Log.d(TAG, "response code : " + response.code())

                    when(response.code()) {
                        201 -> {
                            toast("회원가입에 성공하였습니다.")
                            finish()
                        }

                        404 -> {
                            toast("이미 존재하는 사용자입니다.")
                        }

                        else -> {
                            toast("알 수 없는 오류가 발생하였습니다.\n입력값을 다시 한번 확인해주세요.")
                        }
                    }
                }
            }
        })
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        date = year.toString() + "." + (monthOfYear + 1).toString() + "." + dayOfMonth.toString()
        register_birthday.text = date
    }

}