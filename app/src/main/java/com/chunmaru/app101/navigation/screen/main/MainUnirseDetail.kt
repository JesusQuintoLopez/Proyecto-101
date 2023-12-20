package com.chunmaru.app101.navigation.screen.main

sealed class MainUnirseDetail(val route:String) {
    object unirseDetail:MainUnirseDetail("main/unirse/detail/{uid}"){
        fun passUID(param:String) = "main/unirse/detail/$param"
    }
}