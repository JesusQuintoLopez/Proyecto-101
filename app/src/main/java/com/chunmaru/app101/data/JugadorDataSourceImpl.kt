package com.chunmaru.app101.data

import android.util.Log
import com.chunmaru.app101.data.dao.JugadorDao
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class JugadorDataSourceImpl(private val jugadorDao:JugadorDao):JugadorDataSource {
    override suspend fun insert(jugador: JugadorEntity) {
        jugadorDao.insert(jugador)
    }

    override suspend fun insertAll(jugadores: List<JugadorEntity>) {
        jugadorDao.insertAll(jugadores)
    }

    override fun getJugadores(): Flow<Resource<List<JugadorEntity>>> = flow {
        jugadorDao.getJugadores().collect(){
            it.run {
                Log.d("getJ","$it")
                try {
                    emit(Resource.Success(it))
                    if (it.isNullOrEmpty()) emit(Resource.Failure("fallaaa"))
                }catch(e:Exception){
                    Log.d("getJugadoresError: ","${e.localizedMessage}")
                }

            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun update(id: String, name: String, puntaje: Int, numElim: Int, deuda: Int,estado:Boolean) {
        jugadorDao.update(id,name,puntaje,numElim,deuda,estado)
    }

    override suspend fun delete() {
        jugadorDao.delete()
    }

}