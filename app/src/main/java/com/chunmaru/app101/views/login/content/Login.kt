package com.chunmaru.app101.views.login.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chunmaru.app101.navigation.screen.auth.AuthScreen
import com.chunmaru.app101.utils.Resource
import com.chunmaru.app101.views.login.LoginViewModel

@Composable
fun Login(paddingValues: PaddingValues, navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
    when (val response = vm.isRegister) {
        is Resource.Failure -> {

        }

        Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Success -> {
                if (response.data){
                navController.navigate(route = AuthScreen.Main.route) {
                    popUpTo(AuthScreen.Main.route) { inclusive = true }
                }}else{
                    LoginContent(paddingValues = paddingValues, navController = navController)
                }

        }

        null -> {

        }
    }
}