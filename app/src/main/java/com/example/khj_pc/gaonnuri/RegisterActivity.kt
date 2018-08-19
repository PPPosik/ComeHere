package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(){

    private var registerIntent : Intent? = null
    private lateinit var hint_tmp : String
    private lateinit var input : InputMethodManager

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

        register_name.onFocusChangeListener = editTextFocusListener
        register_email.onFocusChangeListener = editTextFocusListener
        register_birthday.onFocusChangeListener = editTextFocusListener
        register_address.onFocusChangeListener = editTextFocusListener
        register_password.onFocusChangeListener = editTextFocusListener

        register_back.setOnClickListener {
            onBackPressed()
        }

        register_ok_button.setOnClickListener {
            toast("Register OK")
            registerIntent = Intent(this, LoginActivity::class.java)
            startActivity(registerIntent)
        }
    }
}