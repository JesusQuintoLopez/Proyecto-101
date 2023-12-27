package com.chunmaru.app101.navigation.screen.main

import com.chunmaru.app101.R

sealed class MainScreen(val title:String,val route:String,val icon:Int){
    object Home:MainScreen("Home","main/home",R.drawable.ic_home)
    object NuevaPartida:MainScreen("Nueva partida","main/nueva",R.drawable.ic_nueva_partida)
    object unirsePartida:MainScreen("Unirse","main/unirse/List",R.drawable.ic_eyes)
    object perfil:MainScreen("Perfil","main/perfil/detail",R.drawable.ic_person)
}
