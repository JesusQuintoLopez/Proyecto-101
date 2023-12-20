package com.chunmaru.app101.navigation.graph.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.screen.main.MainUnirseDetail
import com.chunmaru.app101.views.main.verpartida.detail.VerPartidaDetailView

fun NavGraphBuilder.MainUnirseGraph(navController: NavHostController) {
    navigation(
        route = Graph.MAIN_VISUALIZAR,
        startDestination = MainUnirseDetail.unirseDetail.route
    ) {
        composable(
            route = MainUnirseDetail.unirseDetail.route,
            arguments = listOf(navArgument("uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")?.let {
                VerPartidaDetailView(navController = navController,it)
            }
        }
    }
}