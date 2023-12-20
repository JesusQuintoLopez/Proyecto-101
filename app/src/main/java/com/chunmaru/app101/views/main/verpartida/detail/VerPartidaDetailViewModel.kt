package com.chunmaru.app101.views.main.verpartida.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.model.PartidaResponse
import com.chunmaru.app101.utils.Resource
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerPartidaDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle)
    :ViewModel() {
    val uid = savedStateHandle.get<String>("uid")
    val firestore = Firebase.firestore
    var jugadoresResponse by mutableStateOf<Resource<List<JugadorEntity>>?>(null)
    init {
        getJugadores()
    }
    fun getJugadores(){
        jugadoresResponse = Resource.Loading
        firestore.collection("partida")
            .document(uid.toString()).
            collection("jugador").addSnapshotListener(){
                    querySnapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                val documents = mutableListOf<JugadorEntity>()
                if (querySnapshot != null){
                    for (document in querySnapshot){
                        val myDocument = document.toObject(JugadorEntity::class.java)
                        documents.add(myDocument)
                    }
                }
                jugadoresResponse = Resource.Success(documents)
                //partidaResponse = Resource.Success(documents)
                Log.d("jugadoresss " ,"${documents.toString()}")
            }
    }

}