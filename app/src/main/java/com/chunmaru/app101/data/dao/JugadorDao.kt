package com.chunmaru.app101.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.utils.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jugador:JugadorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jugadores:List<JugadorEntity>)

    @Query("SELECT * FROM JUGADOR")
    fun getJugadores():Flow<List<JugadorEntity>>

    @Query("UPDATE jugador SET name = :name, puntaje = :puntaje, numelim = :numElim, deuda = :deuda, estado = :estado WHERE id = :id")
    suspend fun update(id:String,name:String,puntaje:Int,numElim:Int,deuda:Int,estado:Boolean)

    @Query("DELETE FROM jugador")
    suspend fun delete()
}