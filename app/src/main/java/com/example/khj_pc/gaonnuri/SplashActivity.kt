package com.example.khj_pc.gaonnuri

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)
        var intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}