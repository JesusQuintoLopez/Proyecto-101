package com.chunmaru.app101.views.main.verpartida.detail.content

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.app101.utils.Resource
import com.chunmaru.app101.views.main.verpartida.detail.VerPartidaDetailViewModel

@Composable
fun GetJugadores(vm:VerPartidaDetailViewModel = hiltViewModel()){
    when(val response = vm.jugadoresResponse){
        Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Success -> {
            VerPartidaDetailContent(response.data)
        }

        is Resource.Failure -> {

        }

        null -> {

        }
    }
}