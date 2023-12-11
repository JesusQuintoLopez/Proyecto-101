package com.chunmaru.app101.views.main.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chunmaru.app101.views.main.profile.content.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(navController: NavHostController){
    Text(text = "profile view")
    Scaffold {
            ProfileContent(paddingValues = it)
    }

}