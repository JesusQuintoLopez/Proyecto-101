package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.app101.utils.Resource
import com.chunmaru.app101.views.main.nuevapartida.NuevaPartidaViewModel

@Composable
fun NuevaPartida(vm:NuevaPartidaViewModel = hiltViewModel()){

    when(val response = vm.responseJugadores){
        Resource.Loading -> { CircularProgressIndicator() }
        is Resource.Success -> {
            LaunchedEffect(Unit){
                if (vm.stateJugadores.size==0){
                vm.stateJugadores.addAll(response.data)
                vm.transition =3
                }
            }
            //vm.updateReturnGame()
        }
        is Resource.Failure -> {
        }
        null -> { }
        else -> {}
    }

}