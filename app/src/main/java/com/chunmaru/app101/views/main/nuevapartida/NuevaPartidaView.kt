package com.chunmaru.app101.views.main.nuevapartida

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chunmaru.app101.views.main.nuevapartida.content.NuevaPartida
import com.chunmaru.app101.views.main.nuevapartida.content.NuevaPartidaContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaPartidaView(navController: NavHostController){
    Scaffold {
        NuevaPartidaContent(it)
    }
    NuevaPartida()
}