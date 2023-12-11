package com.chunmaru.app101.views.main.bottomNav.content

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chunmaru.app101.navigation.screen.main.MainScreen
import com.chunmaru.app101.ui.theme.Purple40

@Composable
fun MainBottomBar(navController: NavHostController) {

    val screens = listOf(
        MainScreen.Home,
        MainScreen.NuevaPartida,
        MainScreen.unirsePartida,
        MainScreen.perfil
    )

    val navBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(containerColor = Purple40) {
            screens.forEach { screen ->
                MainBottomBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}