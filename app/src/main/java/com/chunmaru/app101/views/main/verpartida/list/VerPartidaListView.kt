package com.chunmaru.app101.views.main.verpartida.list

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chunmaru.app101.views.main.verpartida.list.content.GetPartidas
import com.chunmaru.app101.views.main.verpartida.list.content.VerPartidaListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerPartidaListView(navController: NavHostController){
    Scaffold() {

    }
    GetPartidas(navController)
}