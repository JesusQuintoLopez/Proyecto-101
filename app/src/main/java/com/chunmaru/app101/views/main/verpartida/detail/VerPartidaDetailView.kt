package com.chunmaru.app101.views.main.verpartida.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chunmaru.app101.R
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.views.main.verpartida.detail.content.GetJugadores
import com.chunmaru.app101.views.main.verpartida.detail.content.VerPartidaDetailContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerPartidaDetailView(navController: NavHostController, uidDoc: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registro",
                        color = Color.White,
                        fontSize = 20.sp)
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        tint = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Purple40
                )
            )
        }
    ) {
        GetJugadores()
    }


}