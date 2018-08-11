package com.example.khj_pc.gaonnuri

import com.example.khj_pc.gaonnuri.Data.*
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("/users/authenticate")
    fun login(@Body user : User) : Call<LoginResult>

    @POST("/users/register")
    fun register(@Body user : User) : Call<Result>

    //토큰 필요
    @POST("/users/profile/{id}")
    fun getUserInfo(@Path("id") id : String) : Call<UserResult>
}

interface RoomService {
    @POST("/room/create")
    fun createRoom(@Body room : Room) : Call<Result>

    @POST("/room/search/{sub_string}")
    fun search(@Path("sub_string") subString : String) : Call<RoomResult>

    @POST("/room/enter/{id}/{objId}")
    fun enter(@Path("id") id : String, @Path("objId") objectId : String) : Call<Result>

    @GET("/room/get_room/{objId}")
    fun getSingleRoomInfo(@Path("obj_id") objectId : String) : Call<SingleRoomResult>
}