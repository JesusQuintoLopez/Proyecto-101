package com.chunmaru.app101.views.main.nuevapartida

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chunmaru.app101.model.JugadorEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NuevaPartidaViewModel @Inject constructor() : ViewModel() {
    var transition by mutableStateOf(0)
    var state by mutableStateOf(NuevaPartidaState())
        private set

    var errorMessage = ""
    var showAlert by mutableStateOf(false)
    var stateJugadores = mutableStateListOf<JugadorEntity>()
    val listJug1 = mutableStateListOf<String>("0")
    val listJug2 = mutableStateListOf<String>("0")
    val listJug3 = mutableStateListOf<String>("0")
    val listJug4 = mutableStateListOf<String>("0")


    fun onValueNumJug(value: String) {
        state = state.copy(numJugadores = value)
    }

    fun onValueJug1(value: String) {
        state = state.copy(jugador1 = value)
    }

    fun onValueJug2(value: String) {
        state = state.copy(jugador2 = value)
    }

    fun onValueJug3(value: String) {
        state = state.copy(jugador3 = value)
    }

    fun onValueJug4(value: String) {
        state = state.copy(jugador4 = value)
    }

    fun onValueApuesta(value: String) {
        state = state.copy(apuesta = value)
    }

    fun onValuePunt1(value: String) {
        state = state.copy(punt1 = value)
    }

    fun onValuePunt2(value: String) {
        state = state.copy(punt2 = value)
    }

    fun onValuePunt3(value: String) {
        state = state.copy(punt3 = value)
    }

    fun onValuePunt4(value: String) {
        state = state.copy(punt4 = value)
    }


    fun validateForm(): Boolean {
        if (state.numJugadores.equals("")) {
            errorMessage = "ingrese un numero entre 2 y 4"
            return false
        } else if (state.numJugadores == "2" && (state.jugador1.equals("") || state.jugador2.equals(
                ""
            ))
        ) {
            errorMessage = "rellene todos los campos"
            return false
        }
        return true
    }

    fun iniciarPartida() {
        for (i in 1..state.numJugadores.toInt()) {
            if (i == 1) stateJugadores.add(
                JugadorEntity(
                    id = "j1",
                    name = state.jugador1,
                    deuda = state.apuesta.toInt()
                )
            )

            if (i == 2) stateJugadores.add(
                JugadorEntity(
                    id = "j2",
                    name = state.jugador2,
                    deuda = state.apuesta.toInt()
                )
            )

            if (i == 3) stateJugadores.add(
                JugadorEntity(
                    id = "j3",
                    name = state.jugador3,
                    deuda = state.apuesta.toInt()
                )
            )

            if (i == 4) stateJugadores.add(
                JugadorEntity(
                    id = "j4",
                    name = state.jugador4,
                    deuda = state.apuesta.toInt()
                )
            )
        }

        Log.d("iniciarPar", stateJugadores.size.toString())
    }

    fun RegistrarPuntos() {
        listJug1.add(state.punt1)
        listJug2.add(state.punt2)
        listJug3.add(state.punt3)
        listJug4.add(state.punt4)

        suma(state.numJugadores)
    }

    private fun suma(nJug: String) {
        if (nJug.equals("1") || nJug.equals("2") || nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(0)
            stateJugadores.set(0,aux.copy(puntaje = aux.puntaje+state.punt1.toInt()))
        }
        if (nJug.equals("2") || nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(1)
            stateJugadores.set(1,aux.copy(puntaje = aux.puntaje+state.punt2.toInt()))
        }
        if (nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(2)
            stateJugadores.set(2,aux.copy(puntaje = aux.puntaje+state.punt3.toInt()))
        }
        if (nJug.equals("4")) {
            val aux = stateJugadores.get(3)
            stateJugadores.set(3,aux.copy(puntaje = aux.puntaje+state.punt4.toInt()))
        }
    }


}

