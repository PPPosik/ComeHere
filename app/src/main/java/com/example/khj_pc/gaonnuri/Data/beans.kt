package com.example.khj_pc.gaonnuri.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(val message : String)

data class LoginResult(val id : String, val token : String, val message : String)

data class LoginUser(val id : String, val password : String)

data class User(val id : String, val name : String, val password : String) {
    var address : String = ""
    var birth: String = ""
}

data class UserResult(val _id : String, val name : String, val auth_id : String, val hashed_password : String, val create_at : String, val room_string : List<Room>)

data class Room(val _id : String,
                @SerializedName("quest_name") val questionName : String,
                @SerializedName("request_person_id") val requestPersonId : String,
                val title : String,
                val context : String,
                val location : String,
                @SerializedName("people_num_max") val peopleNumMax : Int,
                @SerializedName("people_num") val peopleNum : Int,
                val users : List<String>,
                val images : List<String>) : Serializable

data class RoomResult(val message : String, val results : List<Room>)

data class SingleRoomResult(val _id : String,
                            @SerializedName("quest_name") val questionName : String,
                            @SerializedName("request_person_id") val requestPersonId : String,
                            val title : String,
                            val context : String,
                            val location : String,
                            @SerializedName("people_num_max") val peopleNumMax : Int,
                            @SerializedName("people_num") val peopleNum : Int,
                            val users : List<String>,
                            val images : List<String>,
                            val message : String)