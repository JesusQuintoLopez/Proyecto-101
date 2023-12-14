package com.chunmaru.app101.di

import android.app.Application
import androidx.room.Room
import com.chunmaru.app101.data.JugadorDataSource
import com.chunmaru.app101.data.JugadorDataSourceImpl
import com.chunmaru.app101.data.dao.JugadorDao
import com.chunmaru.app101.data.db.App101DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun Provide101DB(app: Application): App101DB =
        Room.databaseBuilder(app, App101DB::class.java, "101_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideJugadorDao(db: App101DB): JugadorDao = db.jugadorDao()

    @Provides
    fun providesJugadorDataSource(jugadorDao: JugadorDao): JugadorDataSource =
        JugadorDataSourceImpl(jugadorDao)


}