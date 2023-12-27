package com.chunmaru.app101.navigation.graph.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.screen.main.MainProfileScreen
import com.chunmaru.app101.views.main.profile.update.ProfileUpdateView

fun NavGraphBuilder.MainProfileGraph(naController: NavHostController) {
    navigation(
        route = Graph.MAIN_PROFILE+"/{user}",
        startDestination = MainProfileScreen.ProfileUpdate.route,
    ) {
        composable(
            route = MainProfileScreen.ProfileUpdate.route,
            arguments = listOf(navArgument(name = "user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let {
                ProfileUpdateView(navController = naController, param = it)
            }
        }
    }
}