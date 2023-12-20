package com.chunmaru.app101.views.main.verpartida.list.content

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chunmaru.app101.utils.Resource
import com.chunmaru.app101.views.main.verpartida.list.VerPartidaListViewModel

@Composable fun GetPartidas(navController: NavHostController,vm:VerPartidaListViewModel = hiltViewModel()){
    when(val response = vm.partidaResponse){
        is Resource.Failure -> {}
        Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            VerPartidaListContent(navController,response.data)
        }
        null -> {}
    }
}