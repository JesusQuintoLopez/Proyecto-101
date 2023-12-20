package com.chunmaru.app101.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chunmaru.app101.data.dao.JugadorDao
import com.chunmaru.app101.data.dao.PartidaDao
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.data.entity.PartidaEntity

@Database(
    entities = [JugadorEntity::class,PartidaEntity::class],
    version = 2,
    exportSchema = false
)
abstract class App101DB : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
    abstract fun partidaDao(): PartidaDao
}