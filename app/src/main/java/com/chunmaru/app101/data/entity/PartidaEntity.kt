package com.chunmaru.app101.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "partida")
data class PartidaEntity(
    @PrimaryKey(autoGenerate = true) val pk:Long = 0,
    @ColumnInfo val estado:Boolean = true,
    @ColumnInfo val numJug:Int,
    @ColumnInfo val apuesta:Int,
    @ColumnInfo val ganador:String = ""
)
