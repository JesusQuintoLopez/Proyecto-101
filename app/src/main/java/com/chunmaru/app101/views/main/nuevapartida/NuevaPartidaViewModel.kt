package com.chunmaru.app101.views.main.nuevapartida

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NuevaPartidaViewModel @Inject constructor():ViewModel() {
    var transition by mutableStateOf(0)
    var state by mutableStateOf(NuevaPartidaState())
        private set

    fun onValueNumJug(value:String){
        state = state.copy(numJugadores = value)
    }
    fun onValueJug1(value:String){
        state = state.copy(jugador1 =  value)
    }
    fun onValueJug2(value:String){
        state = state.copy(jugador2 =  value)
    }
    fun onValueJug3(value:String){
        state = state.copy(jugador3 =  value)
    }
    fun onValueJug4(value:String){
        state = state.copy(jugador4 =  value)
    }
    fun onValueApuesta(value:String){
        state = state.copy(apuesta =   value)
    }
}