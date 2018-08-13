package com.example.khj_pc.gaonnuri

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

import java.io.File

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by baehyeonbin on 2017. 9. 3..
 */

object RetrofitUtil {

    var retrofit = Retrofit.Builder()
            .baseUrl("http://purplebeen.kr:2345")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val MULTIPART_FORM_DATA = "multipart/form-data"

    fun getLoginRetrofit(context: Context): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val token : String = SharedPreferenceUtil.getPreference(context, "token")!!
            val request = original.newBuilder()
                    .header("authorization", token)
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }

        val client = httpClient.build()
        return Retrofit.Builder()
                .baseUrl("http://purplebeen.kr:2345")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    fun createRequestBody(file: File, name: String): MultipartBody.Part {
        val mFile = RequestBody.create(MediaType.parse("images/*"), file)
        return MultipartBody.Part.createFormData(name, file.name, mFile)
    }

    fun createRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), value)
    }
}

fun ImageView.loadUrl(url : String) {
    Glide.with(context).load(url).into(this)
}

object SharedPreferenceUtil {
    fun getPreference(context: Context, key: String): String? {
        val sharedPreferences = context.getSharedPreferences("gaonnuri", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun savePreferences(context: Context, key: String, data: String) {
        val sharedPreferences = context.getSharedPreferences("gaonnuri", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.commit()
    }

    fun removePreferences(context: Context, key: String) {
        val pref = context.getSharedPreferences("gaonnuri", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(key)
        editor.commit()
    }
}
