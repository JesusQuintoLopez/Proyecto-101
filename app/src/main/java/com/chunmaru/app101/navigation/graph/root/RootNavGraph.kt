package com.chunmaru.app101.navigation.graph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.graph.auth.AuthNavGraph

@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH ){
        AuthNavGraph(navController = navController)
    }
}