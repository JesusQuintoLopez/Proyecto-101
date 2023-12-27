package com.chunmaru.app101.navigation.screen.main

sealed class MainProfileScreen(val route:String){
    object ProfileUpdate:MainProfileScreen(route = "main/profile/update/{user}"){
        fun PassUser(param:String) = "main/profile/update/$param"
    }
}
