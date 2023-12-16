package com.chunmaru.app101.data

import android.util.Log
import com.chunmaru.app101.data.dao.PartidaDao
import com.chunmaru.app101.data.entity.PartidaEntity
import com.chunmaru.app101.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class PartidaDataSourceImpl(private val partidaDao:PartidaDao):PartidaDataSource {
    override suspend fun insertPartida(partida: PartidaEntity):Long{
       return partidaDao.insertPartida(partida)
    }
    override suspend fun insertAllPartidas(partidas: List<PartidaEntity>) = partidaDao.insertAllPartidas(partidas)

    override fun getPartidas(): Flow<Resource<List<PartidaEntity>>> = flow {
        partidaDao.getPartidas().collect(){
            it.run {
                try {
                    emit(Resource.Success(it))
                    if (it.isNullOrEmpty())emit(Resource.Failure("no hay registro de partidas"))
                }catch (e:IOException){
                    Log.d("getPartidasError: ","${e.localizedMessage}")
                }

            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getPartida(): Flow<Resource<List<PartidaEntity>>> = flow {
        partidaDao.getPartida().collect(){
            it.run {
                try {
                    emit(Resource.Success(it))
                    if (it.isNullOrEmpty())emit(Resource.Failure("no hay registro de partidas"))
                }catch (e:IOException){
                    Log.d("getPartidasError: ","${e.localizedMessage}")
                }

            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun delete() = partidaDao.delete()

}