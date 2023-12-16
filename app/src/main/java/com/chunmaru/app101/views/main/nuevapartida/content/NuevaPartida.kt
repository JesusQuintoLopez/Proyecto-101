package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.app101.utils.Resource
import com.chunmaru.app101.views.main.nuevapartida.NuevaPartidaViewModel
import kotlinx.coroutines.delay

@Composable
fun NuevaPartida(vm:NuevaPartidaViewModel = hiltViewModel()){

    when(val response = vm.responseJugadores){
        Resource.Loading -> { CircularProgressIndicator() }
        is Resource.Success -> {
            LaunchedEffect(Unit){
                if (vm.stateJugadores.size==0){
                    vm.stateJugadores.addAll(response.data)
                    //vm.updateReturnGame()
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

    when(val res = vm.responsePartida){
        is Resource.Failure -> {

        }
        Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            LaunchedEffect(Unit){

                if (res.data.get(0).estado){
                    vm.updateApuAndJug(res.data.get(0),res.data.get(0).pk)
                }else{
                    vm.transition = 0
                }
            }
        }
        null -> {

        }
    }

}