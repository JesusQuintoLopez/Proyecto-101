package com.chunmaru.app101.views.register

data class RegisterState(
    val nombres:String = "",
    val apellidos:String = "",
    val email:String = "",
    val password:String = "",
    val confirmPassword:String = ""
)
