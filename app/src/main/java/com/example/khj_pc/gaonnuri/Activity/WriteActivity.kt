package com.example.khj_pc.gaonnuri.Activity

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import org.jetbrains.anko.toast
import android.view.LayoutInflater
import android.view.View
import com.example.khj_pc.gaonnuri.Data.Board
import com.example.khj_pc.gaonnuri.Data.Result
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Service.PostService
import com.example.khj_pc.gaonnuri.Util.RetrofitUtil
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil
import com.example.khj_pc.gaonnuri.Util.loadImage
import kotlinx.android.synthetic.main.content_write.*
import kotlinx.android.synthetic.main.item_write_image.view.*
import okhttp3.MultipartBody
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class WriteActivity : Activity(), EasyPermissions.PermissionCallbacks {

    private lateinit var itemView : View
    private val images : ArrayList<MultipartBody.Part> = ArrayList()

    private var uri : Uri? = null
    lateinit var file : File
    private val REQUEST_GALLERY_CODE =200
    private val READ_REQUEST_CODE = 300

    lateinit var roomId : String

    companion object {
        val TAG : String = WriteActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        roomId = intent.getStringExtra("roomId")
        addView()

        setListeners()
    }

    fun setListeners() {
        sendButton.setOnClickListener {
            if(titleEditText.text.toString().isNotEmpty() && contextEditText.text.toString().isNotEmpty() && images.size > 0) {
                Log.d("TAG", "start send")
                createPost()
            } else {
                toast("제목과 내용, 그리고 이미지를 선택해주세요!")
            }
        }
    }

    fun createPost() {
        val postService : PostService = RetrofitUtil.getLoginRetrofit(applicationContext).create(PostService::class.java)
        val name : String = SharedPreferenceUtil.getPreference(applicationContext, "name")!!
        val id : String = SharedPreferenceUtil.getPreference(applicationContext, "username")!!
        val post : Board = Board(roomId, id, name, titleEditText.text.toString(), contextEditText.text.toString(), images.size)
        val call : Call<Result> = postService.createPost(post, images)
        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                Log.e(TAG, response.code().toString())
                if(response.body() != null) {
                    when(response.code()) {
                        200 -> {
                            Log.d(TAG, "successful upload, message is ${response.body()!!.message}")
                            toast("성공적으로 게시글을 작성하였습니다!")
                            finish()
                        }
                        else -> {
                            Log.e(TAG, "error, error http code is ${response.code()}")
                        }
                    }
                } else {

                }
            }

        })
    }

    fun addView() {
        val layoutInflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        itemView = LayoutInflater.from(this).inflate(R.layout.item_write_image, linearLayout, false)
        itemView.setOnClickListener{
            if(EasyPermissions.hasPermissions(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                getImage()
            } else {
                EasyPermissions.requestPermissions(this, "이미지를 가져오기 위해서 권한이 필요합니다", 300, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        linearLayout.addView(itemView)
    }


    fun getImage(){
        val openGalleryIntent = Intent(Intent.ACTION_PICK)
        openGalleryIntent.type="image/*"
        startActivityForResult(openGalleryIntent,REQUEST_GALLERY_CODE)
    }

    private fun getRealPathFromURI(contentURI: Uri,activity: Activity):String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        //contentResolver라는 db에서 해당 URI를 탐색할수있는 cursor객체를 받아옴.
        return if (cursor == null) {
            contentURI.path
        } else {
            cursor.moveToFirst() //커서의 위치를 맨 앞인 첫 번째로 옮겨서
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)//data 컬럼의 인덱스를 가져옴.
            cursor.getString(idx) //해당하는 인덱스의 실제 path를 String으로 가져옴.
        }
    }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_GALLERY_CODE && resultCode ==Activity.RESULT_OK){
            uri = data!!.data//사진 data를 가져옴.
            if(EasyPermissions.hasPermissions(applicationContext,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                itemView.imageCard.item_write_image_src.loadImage(uri!!,applicationContext)
                itemView.imageCard.item_write_image_no_img.visibility = View.GONE
                val filePath : String = getRealPathFromURI(uri!!,this) //실제 path가 담김.
                file = File(filePath)
                images.add(RetrofitUtil.createRequestBody(file, "images"))
                Log.d(TAG, "image list size = ${images.size}")
                addView()
            }else{
                EasyPermissions.requestPermissions(this@WriteActivity,"파일을 읽기 위해서는 권한이 필요합니다!",READ_REQUEST_CODE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        }
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        EasyPermissions.requestPermissions(this, "이미지를 가져오기 위해서 권한이 필요합니다", 300, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        getImage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


}
