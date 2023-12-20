package com.chunmaru.app101.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jugador")
data class JugadorEntity(
    @PrimaryKey(autoGenerate = true) val pk:Long = 0,
    @ColumnInfo val id:String = "",
    @ColumnInfo val name:String = "",
    @ColumnInfo val puntaje:Int = 0,
    @ColumnInfo val numElim:Int = 0,
    @ColumnInfo val deuda:Int = 0,
    @ColumnInfo val estado:Boolean = true,
    @ColumnInfo val partida_pk:Long? = null
)
