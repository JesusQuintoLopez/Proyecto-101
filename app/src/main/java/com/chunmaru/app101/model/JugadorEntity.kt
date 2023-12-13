package com.chunmaru.app101.model

data class JugadorEntity(
    val id:String = "",
    val name:String = "",
    val puntaje:Int = 0,
    val puntaAux:Int = 0,
    val numElim:Int = 0,
    val deuda:Int = 0,
    val estado:Boolean = true
)
