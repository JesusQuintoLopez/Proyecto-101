package com.chunmaru.app101.model

data class UserModel(
    val userId:String,
    val name:String,
    val lastName:String,
    val email:String
){
    fun toMap() = mutableMapOf(
        "userId" to this.userId,
        "name" to this.name,
        "lastName" to this.lastName,
        "email" to this.email,
    )
}
