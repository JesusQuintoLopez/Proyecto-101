package com.chunmaru.app101.views.main.verpartida.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.model.PartidaResponse
import com.chunmaru.app101.utils.Resource
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class VerPartidaListViewModel @Inject constructor():ViewModel() {
    val fireStore = Firebase.firestore
    var partidaResponse by mutableStateOf<Resource<List<PartidaResponse>>?>(null)
    init {
        getPartidas()
    }

    fun getPartidas()=viewModelScope.launch {
        partidaResponse = Resource.Loading
        fireStore.collection("partida").whereEqualTo("date",formatDate()).addSnapshotListener(){
                querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            val documents = mutableListOf<PartidaResponse>()
            if (querySnapshot != null){
                for (document in querySnapshot){
                   val myDocument = document.toObject(PartidaResponse::class.java).copy(idDoc = document.id)
                    documents.add(myDocument)
                }
            }
            partidaResponse = Resource.Success(documents)
            Log.d("party " ,"${documents.toString()}")
        }
    }

    private fun formatDate(): String {
        val currentDate = Calendar.getInstance().time
        val res = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return res.format(currentDate)
    }
}