package com.example.khj_pc.gaonnuri

import com.example.khj_pc.gaonnuri.Data.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("user/authenticate")
    fun login(@Body user : LoginUser) : Call<LoginResult>

    @POST("user/register")
    fun register(@Body user : User) : Call<Result>

    //토큰 필요
    @POST("user/profile/{id}")
    fun getUserInfo(@Path("id") id : String) : Call<UserResult>
}

interface RoomService {
    @POST("room/create")
    fun createRoom(@Body room : Room) : Call<Result>

    @POST("room/search/{sub_string}")
    fun search(@Path("sub_string") subString : String) : Call<RoomResult>

    @POST("room/enter/{id}/{objId}")
    fun enter(@Path("id") id : String, @Path("objId") objectId : String) : Call<Result>

    @GET("room/get_room/{obj_id}")
    fun getSingleRoomInfo(@Path("obj_id") objectId : String) : Call<SingleRoomResult>

    @GET("room/get_room_all")
    fun getAllRoomData() : Call<List<Room>>
}

interface PostService {
    @GET("post/get_all_post/{room_id}")
    fun getAllPostFromRoomId(@Path("room_id") roomId : String) : Call<ArrayList<Board>>

    @GET("post/get_top_3/{room_id}")
    fun getTopThree(@Path("room_id") roomId : String) : Call<ArrayList<Board>>

    @GET("post/add_like/{room_id}/{post_id}")
    fun addLike(@Path("room_id") roomId : String, @Path("post_id") postId : String) : Call<SingleBoardResult>

    @GET("post/get_post/{room_id}/{post_id}")
    fun getOne(@Path("room_id") roomId : String, @Path("post_id") postId : String) : Call<Board>

    @POST("post/add_comment")
    fun addComment(@Body comment : Chat) : Call<Board>

    @Multipart
    @POST("post/create")
    fun createPost(@Part("data") board: Board, @Part images : ArrayList<MultipartBody.Part>) : Call<Result>
}

interface MachineLearningService {
    @POST("/ml")
    fun getMLData(@Body roomId : RoomID) : Call<MLData>
}