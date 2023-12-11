package com.chunmaru.app101.navigation.graph.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.screen.auth.AuthScreen
import com.chunmaru.app101.views.login.LoginView
import com.chunmaru.app101.views.main.bottomNav.MainBottomView

import com.chunmaru.app101.views.register.RegisterView

fun NavGraphBuilder.AuthNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.login.route
    ){
        composable(AuthScreen.login.route){
            LoginView(navController)
        }
        composable(AuthScreen.register.route){
            RegisterView(navController)
        }
        composable(AuthScreen.Main.route){
            MainBottomView()
        }





    }
}