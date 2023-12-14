package com.chunmaru.app101.views.main.nuevapartida.content

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.app101.R
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.ui.theme.Red
import com.chunmaru.app101.views.components.Alert
import com.chunmaru.app101.views.main.nuevapartida.NuevaPartidaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaPartidaContent(paddingValues: PaddingValues, vm: NuevaPartidaViewModel = hiltViewModel()) {
    val activity = LocalContext.current as Activity
    val state = vm.state
    var nJug = vm.state.numJugadores
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

        Column{


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
                        if (vm.validateForm()) {
                            vm.transition = 1
                        } else {
                            vm.showAlert = true
                        }
                    }
                )
            }

            if (vm.transition == 1) {
                Transition1(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    numJug = vm.state.numJugadores,
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
                    onValueBtn = {
                        vm.iniciarPartida()
                        vm.transition = 3
                    }
                )


            }

            if (vm.transition == 3) {
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                            items(vm.stateJugadores) { jug ->
                                ItemName(name = jug.name)
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            if(nJug.equals("1") || nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                OutlinedTextField(
                                    modifier = Modifier.width(60.dp),
                                    value = state.punt1,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    onValueChange = {vm.onValuePunt1(it)},
                                    enabled = vm.stateJugadores.get(0).estado,
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    label = { Text(text = "Ptje", fontSize = 12.sp) },
                                    placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
                                )
                            }

                            if(nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                OutlinedTextField(
                                    modifier = Modifier.width(60.dp),
                                    value = state.punt2,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    onValueChange = {vm.onValuePunt2(it)},
                                    enabled = vm.stateJugadores.get(1).estado,
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    label = { Text(text = "Ptje", fontSize = 12.sp) },
                                    placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
                                )
                            }

                            if( nJug.equals("3") || nJug.equals("4")){
                                OutlinedTextField(
                                    modifier = Modifier.width(60.dp),
                                    value = state.punt3,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    onValueChange = {vm.onValuePunt3(it)},
                                    enabled = vm.stateJugadores.get(2).estado,
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    label = { Text(text = "Ptje", fontSize = 12.sp) },
                                    placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
                                )
                            }

                            if(nJug.equals("4")){
                                OutlinedTextField(
                                    modifier = Modifier.width(60.dp),
                                    value = state.punt4,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    onValueChange = {vm.onValuePunt4(it)},
                                    enabled = vm.stateJugadores.get(3).estado,
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    label = { Text(text = "Ptje", fontSize = 12.sp) },
                                    placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            modifier = Modifier
                                .width(250.dp)
                                .height(50.dp),
                            onClick = { vm.RegistrarPuntos() },
                            colors = ButtonDefaults.buttonColors(containerColor = Red)) {
                            Text(text = "Registrar puntos", color = Color.White)
                        }
                    }
                }

                Card(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(25.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Text(text = "Historial de puntuaciones", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { vm.showInfo = true }) {
                                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_info),
                                    contentDescription = "",
                                    tint = Red)
                            }
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .padding(top = 15.dp, end = 15.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            if(nJug.equals("1") || nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                                    items(vm.listJug1){
                                        Text(text = it)
                                    }
                                }
                            }

                            if( nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                                    items(vm.listJug2){
                                        Text(text = it)
                                    }
                                }
                            }

                            if(nJug.equals("3") || nJug.equals("4")){
                                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                                    items(vm.listJug3){
                                        Text(text = it)
                                    }
                                }
                            }

                            if(nJug.equals("4")){
                                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                                    items(vm.listJug4){
                                        Text(text = it)
                                    }
                                }
                            }

                        }

                        Divider(color = Purple40)

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp, end = 15.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                            if(nJug.equals("1") || nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                Text(text = vm.stateJugadores.get(0).puntaje.toString())
                            }
                            if(nJug.equals("2") || nJug.equals("3") || nJug.equals("4")){
                                Text(text = vm.stateJugadores.get(1).puntaje.toString())
                            }
                            if(nJug.equals("3") || nJug.equals("4")){
                                Text(text = vm.stateJugadores.get(2).puntaje.toString())
                            }
                            if(nJug.equals("4")){
                                Text(text = vm.stateJugadores.get(3).puntaje.toString())
                            }

                        }

                }
                Row(modifier = Modifier.padding(start = 38.dp, end = 38.dp, top = 15.dp)) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Red, contentColor = Color.White),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                        ,onClick = {  }) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_upload),
                            contentDescription = "")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Red, contentColor = Color.White),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .shadow(elevation = 10.dp),
                        onClick = {
                            vm.end()
                        }) {
                        Icon(modifier = Modifier.size(24.dp),imageVector = ImageVector.vectorResource(id = R.drawable.ic_end),
                            contentDescription = "")
                    }

                }

            }


            if (vm.showAlert) {
                Alert(
                    title = "Error Form",
                    message = vm.errorMessage,
                    confirmText = "Aceptar",
                    onConfirmClick = { vm.showAlert = false }) {

                }
            }

            if (vm.jugadoresEliminados.size>0 && (vm.stateJugadores.size>vm.jugadoresEliminados.size+1)){
                AlertDialog(
                    title = { Text(
                        fontSize = 14.sp,
                        text = "Hay ${vm.jugadoresEliminados.size} jugador(es) eliminado(s), Seleccione los jugadores que van a continuar")},
                    text = {
                           Column {
                               vm.jugadoresEliminados.map {jug->
                                   Row {
                                   Text(text = jug.name)
                                   Checkbox(
                                       checked = vm.stateJugadores.get(jug.id.toInt()).estado,
                                       onCheckedChange = {
                                           val aux = vm.stateJugadores.get(jug.id.toInt())
                                           vm.stateJugadores.set(jug.id.toInt(),aux.copy(estado = it)) }
                                   )
                                   }
                               }
                           }
                    },
                    onDismissRequest = {  },
                    confirmButton = {
                        Button(
                            onClick = { vm.AceptarShowAlertElim() },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Red)) {
                            Text(text = "Aceptar")
                        }
                    })


            }

            if (vm.jugadoresEliminados.size>0 && (vm.stateJugadores.size==vm.jugadoresEliminados.size+1)){
                var ganador = ""
                vm.stateJugadores.map {jug->
                    if(jug.estado) ganador=jug.name
                }
                AlertDialog(
                    title = { Text(text = "Victoria por parte de $ganador")},
                    onDismissRequest = { /*TODO*/ },
                    confirmButton = {
                        Button(
                        onClick = { vm.AceptarShowAlertElim() },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Red)) {
                        Text(text = "Aceptar")
                    } })
            }

            if (vm.showInfo){
                AlertDialog(
                    onDismissRequest = { /*TODO*/ },
                    text = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            LazyRow(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                                items(vm.stateJugadores){jug->
                                    Text(text = jug.name, fontWeight = FontWeight.Bold, color = Red)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(text = "Puntuacion", fontWeight = FontWeight.Bold)
                            LazyRow(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                                items(vm.stateJugadores){jug->
                                    Text(text = jug.puntaje.toString())
                                }
                            }
                            Text(text = "numero de eliminados", fontWeight = FontWeight.Bold)
                            LazyRow(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                                items(vm.stateJugadores){jug->
                                    Text(text = jug.numElim.toString())
                                }
                            }
                            Text(text = "Apuesta en juego", fontWeight = FontWeight.Bold)
                            LazyRow(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                                items(vm.stateJugadores){jug->
                                    Text(text = jug.deuda.toString())
                                }
                            }
                        }

                    },
                    confirmButton = {
                        Button(onClick = { vm.showInfo = false },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Red)) {
                            Text(text = "Miniminzar")
                        }
                    }
                )

            }

        }

    }

}

