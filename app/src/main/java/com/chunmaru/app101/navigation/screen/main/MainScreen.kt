package com.chunmaru.app101.navigation.screen.main

import com.chunmaru.app101.R

sealed class MainScreen(val title:String,val route:String,val icon:Int){
    object Home:MainScreen("Home","main_home",R.drawable.ic_home)
    object NuevaPartida:MainScreen("Nueva partida","main_nueva",R.drawable.ic_nueva_partida)
    object unirsePartida:MainScreen("Unirse","main_unirse",R.drawable.ic_eyes)
    object perfil:MainScreen("Perfil","main_perfil",R.drawable.ic_person)
}
