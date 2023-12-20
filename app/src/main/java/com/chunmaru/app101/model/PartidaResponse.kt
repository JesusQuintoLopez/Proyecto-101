package com.chunmaru.app101.model


data class PartidaResponse(
    val estado: Boolean? = null,
    val numJug: Int? = null,
    val apuesta: Int? = null,
    val ganador: String = "",
    val email:String = "",
    val date:String = "",
    val idDoc:String = ""
)
