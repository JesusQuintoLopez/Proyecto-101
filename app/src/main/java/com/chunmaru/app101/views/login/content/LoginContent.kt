package com.chunmaru.app101.views.login.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.chunmaru.app101.R
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.graph.main.MainNavGraph
import com.chunmaru.app101.views.components.Alert
import com.chunmaru.app101.navigation.screen.auth.AuthScreen
import com.chunmaru.app101.ui.theme.Red
import com.chunmaru.app101.views.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    vm: LoginViewModel = hiltViewModel()
) {

    val state = vm.state
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(0.75f, 0.75f, 0.75f, 1f)
            })
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(top = 50.dp),
                painter = painterResource(R.drawable.loogo),
                contentDescription = "logo",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .padding(horizontal = 30.dp, vertical = 30.dp)
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.8f))
            ) {
                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
                    Text(text = "INGRESAR", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedTextField(
                        value = state.email,
                        onValueChange = { vm.onValueEmail(it) },
                        label = { Text(text = "Correo Electronico") },
                        placeholder = { Text(text = "Ingrese su correo") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = ""
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { vm.onValuePassword(it) },
                        label = { Text(text = "Contraseña") },
                        placeholder = { Text(text = "Ingrese su contraseña") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = ""
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            vm.login(state.email, state.password) {

                                navController.navigate(route = AuthScreen.Main.route) {
                                    popUpTo(AuthScreen.Main.route) { inclusive = true }
                                }
                            }

                        },
                        colors = ButtonDefaults.buttonColors(Red)
                    ) {
                        Text(text = "LOGIN")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "¿No tiene una cuenta?", fontSize = 16.sp)
                        Text(
                            modifier = Modifier.clickable {
                                navController.navigate(AuthScreen.register.route)
                            },
                            text = "Registrate",
                            color = Red,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

            }

        }
        if (vm.showAlert) {
            Alert(
                title = "Error",
                message = vm.errorMessage,
                confirmText = "Aceptar",
                onConfirmClick = {
                    vm.showAlert = false
                }) {

            }
        }
    }
}