package com.chunmaru.app101.model

import com.google.gson.Gson
import java.io.Serializable

data class User(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
    val userId:String = ""
):Serializable{
//    fun toJson():String = Gson().toJson(
//        User
//    )
}
