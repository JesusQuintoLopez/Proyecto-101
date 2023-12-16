package com.chunmaru.app101.data

import com.chunmaru.app101.data.entity.PartidaEntity
import com.chunmaru.app101.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PartidaDataSource {
    suspend fun insertPartida(partida: PartidaEntity):Long
    suspend fun insertAllPartidas(partidas:List<PartidaEntity>):List<Long>
    fun getPartidas(): Flow<Resource<List<PartidaEntity>>>
    fun getPartida(): Flow<Resource<List<PartidaEntity>>>
    suspend fun delete()
}