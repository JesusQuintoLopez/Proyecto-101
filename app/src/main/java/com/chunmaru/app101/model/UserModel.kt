package com.chunmaru.app101.model

import com.google.gson.Gson

data class UserModel(
    val userId:String= "",
    val name:String= "",
    val lastName:String= "",
    val email: String= "",
    val image: String = "",
){
    fun toMap() = mutableMapOf(
        "userId" to this.userId,
        "name" to this.name,
        "lastName" to this.lastName,
        "email" to this.email,
        "email" to this.image
    )

    fun toJson() = Gson().toJson(
        UserModel(userId, name, lastName, email, image)
    )
    companion object{ fun FromJson(data:String):UserModel = Gson().fromJson(data,UserModel::class.java) }
}
