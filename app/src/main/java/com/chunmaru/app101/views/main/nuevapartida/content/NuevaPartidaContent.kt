package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.app101.R
import com.chunmaru.app101.ui.theme.Red
import com.chunmaru.app101.views.main.nuevapartida.NuevaPartidaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaPartidaContent(paddingValues: PaddingValues, vm: NuevaPartidaViewModel = hiltViewModel()) {
    val state = vm.state
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo3),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(0.75f, 0.75f, 0.75f, 1f)
            })
        )

        Column {


            if (vm.transition == 0 || vm.transition == 1) {
                Transition0(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    numJug = state.numJugadores,
                    onValue = {
                        vm.onValueNumJug(it)
                    },
                    {
                        vm.transition = 1
                    }
                )
            }

            if (vm.transition == 1) {
                Transition1(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    numJug = vm.state.numJugadores.toInt(),
                    jug1 = state.jugador1,
                    jug2 = state.jugador2,
                    jug3 = state.jugador3,
                    jug4 = state.jugador4,
                    apuesta = state.apuesta,
                    onValue1 = { vm.onValueJug1(it) },
                    onValue2 = { vm.onValueJug2(it) },
                    onValue3 = { vm.onValueJug3(it) },
                    onValue4 = { vm.onValueJug4(it) },
                    onValueApu = { vm.onValueApuesta(it) },
                    onValueBtn = {vm.transition = 3}
                )


            }



        }

    }

}