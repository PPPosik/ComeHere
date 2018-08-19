package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(){
    private var loginIntent : Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            toast(login_button.text)
        }

        register_button.setOnClickListener {
            toast(register_button.text)
            loginIntent = Intent(this, RegisterActivity::class.java)
            startActivity(loginIntent)
        }

        facebook_button.setOnClickListener{
            toast(facebook_button.text)
        }
    }
}