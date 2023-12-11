package com.chunmaru.app101.views.main.bottomNav

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chunmaru.app101.navigation.graph.main.MainNavGraph
import com.chunmaru.app101.views.main.bottomNav.content.MainBottomBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomView(navController: NavHostController= rememberNavController()){
    Scaffold(
        bottomBar = { MainBottomBar(navController = navController)
    }) {
        MainNavGraph(navController = navController)
    }
}