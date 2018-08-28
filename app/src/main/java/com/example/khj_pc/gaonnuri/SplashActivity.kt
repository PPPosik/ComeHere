package com.example.khj_pc.gaonnuri

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(Runnable {
            Thread.sleep(1000)
            var intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }).start()

    }
}