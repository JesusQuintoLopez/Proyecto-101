package com.chunmaru.app101.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chunmaru.app101.data.dao.JugadorDao
import com.chunmaru.app101.data.entity.JugadorEntity

@Database(
    entities = [JugadorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class App101DB : RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao
}