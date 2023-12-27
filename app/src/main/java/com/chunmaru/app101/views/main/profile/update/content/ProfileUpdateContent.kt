package com.chunmaru.app101.views.main.profile.update.content

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.chunmaru.app101.R
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.views.MainActivity
import com.chunmaru.app101.views.components.DialogCapturePicture
import com.chunmaru.app101.views.main.profile.detail.mapper.ToUserModel
import com.chunmaru.app101.views.main.profile.update.ProfileUpdateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUpdateContent(
    paddingValues: PaddingValues,
    vm: ProfileUpdateViewModel = hiltViewModel()
) {
    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.handle()
    val state = vm.stateProfile

    DialogCapturePicture(
        state = stateDialog,
        takePhoto = { vm.takePhoto() },
        pickImage = { vm.pickImage() }
    )
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
                    )
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .background(
                        color = Purple40,
                        shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
                    )
                    .height(216.dp)
            ) {

                if (vm.stateProfile.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.TopCenter)
                            .clickable { stateDialog.value = true },
                        model = vm.stateProfile.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.TopCenter)
                            .clickable { stateDialog.value = true },
                        painter = painterResource(id = R.drawable.user_image),
                        contentDescription = ""
                    )
                }

            }

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(2.dp, color = Purple40),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(start = 50.dp, top = 25.dp)
            ) {
                Text(
                    text = "Actualiza su Informacion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = state.name,
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
                    value = state.lastName,
                    onValueChange = { vm.onValueLastName(it) },
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

                Button(modifier = Modifier.padding(vertical = 25.dp)
                    .height(50.dp)
                    .width(250.dp),
                    onClick = {
                        //   navController.navigate(route = "${Graph.MAIN_PROFILE}/${vm.stateProfile.ToUserModel().toJson()}")
                    }) {
                    Text(text = "ACTUALIZAR")
                }

            }
        }
    }
}