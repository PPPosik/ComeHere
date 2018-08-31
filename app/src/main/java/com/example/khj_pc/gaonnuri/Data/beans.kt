package com.example.khj_pc.gaonnuri.Data

import android.view.View
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(val message : String)

data class LoginResult(val id : String, val token : String, val message : String, val user : User)

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
                val type : String,
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
data class SingleBoardResult(val message : String, val status : Int, val data : Board)

data class RoomID(val objId : String)

data class MLData(val status : String ,val keyword : ArrayList<Keyword>, val topic_result : TopicResult)

data class Keyword(val key : String, val value : Int)

data class TopicResult(val total : Int, val common : Int, val bathroom : Int, val enter : Int, val lost : Int)

data class SurveyViewModel(val view  : View, val cell : SurveyCell)

data class SurveyCell(var type : Int, var title : String, val body : ArrayList<SurveyBody>, @SerializedName("write_string") var writeString : String)

data class SurveyResultCell(var type : Int, var title : String, val body : ArrayList<SurveyBody>, @SerializedName("write_string") var writeString : ArrayList<String>)

data class SurveyBody(val context : String, var check : Int)

data class SurveyData(@SerializedName("room_id") val roomId : String,
                      @SerializedName("survey_form") val surveyForm : ArrayList<SurveyCell>)

data class SurveyResultData(@SerializedName("room_id") val roomId : String,
                      @SerializedName("survey_form") val surveyForm : ArrayList<SurveyResultCell>)

data class SurveyResponseData(@SerializedName("room_id") val roomId : String,
                          @SerializedName("survey_form") val surveyForm : ArrayList<SurveyCell>,
                              @SerializedName("survey") val survey : ArrayList<SurveyResultCell>,
                              val message : String)

data class SurveyResponse(val message : String, val data : SurveyResponseData)

data class SurveyRoomID(val room_id : String)