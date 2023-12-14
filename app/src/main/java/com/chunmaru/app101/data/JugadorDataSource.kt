package com.chunmaru.app101.data

import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.utils.Resource
import kotlinx.coroutines.flow.Flow

interface JugadorDataSource {
    suspend fun insert(jugador:JugadorEntity)
    suspend fun insertAll(jugadores:List<JugadorEntity>)
    fun getJugadores():Flow<Resource<List<JugadorEntity>>>
    suspend fun update(id:String,name:String,puntaje:Int,numElim:Int,deuda:Int,estado:Boolean)
    suspend fun delete()

}