package com.chunmaru.app101.navigation.graph.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.screen.main.MainScreen
import com.chunmaru.app101.views.main.MainView
import com.chunmaru.app101.views.main.home.HomeView
import com.chunmaru.app101.views.main.nuevapartida.NuevaPartidaView
import com.chunmaru.app101.views.main.profile.ProfileView
import com.chunmaru.app101.views.main.verpartida.VerPartidaView

@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        route = Graph.MAIN,
        startDestination = MainScreen.Home.route ){
        composable(MainScreen.Home.route){
            HomeView(navController = navController)
        }
        composable(MainScreen.NuevaPartida.route){
            NuevaPartidaView(navController = navController)
        }
        composable(MainScreen.unirsePartida.route){
            VerPartidaView(navController = navController)
        }
        composable(MainScreen.perfil.route){
            ProfileView(navController = navController)
        }
    }
}