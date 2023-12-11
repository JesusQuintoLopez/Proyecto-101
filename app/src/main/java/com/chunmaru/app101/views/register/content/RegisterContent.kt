package com.chunmaru.app101.views.register.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chunmaru.app101.R
import com.chunmaru.app101.ui.theme.Red
import com.chunmaru.app101.views.components.Alert
import com.chunmaru.app101.views.register.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    vm: RegisterViewModel = hiltViewModel()
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
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (registerForm, icon) = createRefs()
                val glTop = createGuidelineFromTop(0.15f)
                val glBottom = createGuidelineFromBottom(0.0f)
                Card(
                    modifier = Modifier
                        .constrainAs(registerForm) {

                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(glTop)
                        }
                        .height(550.dp)
                        .width(350.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(0.8f),
                        contentColor = Color.Black
                    )
                ) {

                    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {

                        Text(text = "INGRESAR", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = state.nombres,
                            onValueChange = { vm.onValueName(it) },
                            label = { Text(text = "Nombres") },
                            placeholder = { Text(text = "Ingrese su Nombre") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = ""
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = state.apellidos,
                            onValueChange = { vm.onValueApellidos(it) },
                            label = { Text(text = "Apellidos") },
                            placeholder = { Text(text = "Ingrese su Apellido") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = ""
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(12.dp))
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
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = state.password,
                            onValueChange = { vm.onValuePassword(it) },
                            label = { Text(text = "Contraseña") },
                            placeholder = { Text(text = "Ingrese una contraseña") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = ""
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = state.confirmPassword,
                            onValueChange = { vm.onValuePassword2(it) },
                            label = { Text(text = "Confirmar contraseña") },
                            placeholder = { Text(text = "Confirme su contraseña") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Lock,
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
                                vm.validate()
                                vm.register(){

                                }
                            },
                            colors = ButtonDefaults.buttonColors(Red)
                        ) {
                            Text(text = "REGISTER")
                        }
                    }
                }


                Image(
                    modifier = Modifier
                        .constrainAs(icon) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(registerForm.top, margin = -60.dp)
                        }
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .background(Color.White, shape = CircleShape)
                        .size(100.dp),
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "", contentScale = ContentScale.FillBounds
                )


            }

        }
        if (vm.showAlert) {
            Alert(
                title = "Error",
                message = vm.errorMessage,
                confirmText = "ACEPTAR",
                onConfirmClick = {
                    vm.showAlert = false
                    vm.errorMessage = ""
                }) {

            }
        }
    }
}