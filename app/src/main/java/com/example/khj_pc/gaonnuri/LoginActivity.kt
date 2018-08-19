package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.khj_pc.gaonnuri.Data.LoginResult
import com.example.khj_pc.gaonnuri.Data.LoginUser
import com.example.khj_pc.gaonnuri.Data.User
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(){
    private var loginIntent : Intent? = null

    companion object {
        var TAG : String = LoginActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListeners()
    }

    fun setListeners() {
        login_button.setOnClickListener {
            login()
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

    fun login() {
        var user : LoginUser = LoginUser(login_email.text.toString(), login_password.text.toString())
        var userService : UserService = RetrofitUtil.retrofit.create(UserService::class.java)
        var call : Call<LoginResult> = userService.login(user)
        call.enqueue(object : Callback<LoginResult> {
            override fun onFailure(call: Call<LoginResult>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<LoginResult>?, response: Response<LoginResult>?) {
                Log.d("asdf", "asdf")
                Log.e("asdf", Integer.toString(response!!.code()))
                    when {
                        response.code() == 200 -> {
                            toast("성공적으로 로그인하였습니다!")
                            SharedPreferenceUtil.savePreferences(applicationContext, "token", response.body()!!.token)
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                        response.code() == 401 -> toast("패스워드가 일치하지 않습니다")
                        response.code() == 404 -> toast("존재하지 않는 유저입니다.")
                        else -> Log.e(TAG, response.body()!!.message)
                    }
                }

        })
    }
}