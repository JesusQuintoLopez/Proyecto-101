package com.chunmaru.app101.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chunmaru.app101.data.entity.PartidaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartida(partida:PartidaEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPartidas(partida: List<PartidaEntity>):List<Long>

    @Query("select * from partida")
    fun getPartidas():Flow<List<PartidaEntity>>

    @Query("select  * from partida ORDER BY pk DESC limit 1")
    fun getPartida():Flow<List<PartidaEntity>>

    @Query("UPDATE partida SET estado = :estado, ganador = :ganador WHERE pk = :pk")
    suspend fun updatePartida(pk:Long,estado:Boolean, ganador:String):Int

    @Query("delete from partida")
    suspend fun delete()

}