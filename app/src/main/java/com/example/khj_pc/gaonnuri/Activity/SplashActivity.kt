package com.example.khj_pc.gaonnuri.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.khj_pc.gaonnuri.R

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(Runnable {
            Thread.sleep(1000)
            var intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }).start()

    }
}