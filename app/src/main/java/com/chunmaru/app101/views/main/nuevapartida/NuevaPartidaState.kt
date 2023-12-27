package com.chunmaru.app101.views.main.nuevapartida

data class NuevaPartidaState(
    val numJugadores:String = "4",
    val jugador1:String = "",
    val jugador2:String = "",
    val jugador3:String = "",
    val jugador4:String = "",
    val apuesta:String = "5",
    val punt1:String = "0",
    val punt2:String = "0",
    val punt3:String = "0",
    val punt4:String = "0"
)
