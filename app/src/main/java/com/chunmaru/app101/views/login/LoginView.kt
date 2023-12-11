package com.chunmaru.app101.views.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chunmaru.app101.views.login.content.Login
import com.chunmaru.app101.views.login.content.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavHostController){
    Scaffold {
        Login(paddingValues = it, navController = navController)
    }


}