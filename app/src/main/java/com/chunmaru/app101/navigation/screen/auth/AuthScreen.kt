package com.chunmaru.app101.navigation.screen.auth

sealed class AuthScreen(val route:String){
    object login:AuthScreen("login")
    object register:AuthScreen("register")
    object Main:AuthScreen("Main")
}
