package com.chunmaru.app101.views.main.profile.detail.content

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chunmaru.app101.R
import com.chunmaru.app101.navigation.Graph
import com.chunmaru.app101.navigation.screen.main.MainProfileScreen
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.views.MainActivity
import com.chunmaru.app101.views.components.DialogCapturePicture
import com.chunmaru.app101.views.main.profile.detail.ProfileDetailViewModel
import com.chunmaru.app101.views.main.profile.detail.mapper.ToUserModel

@Composable
fun ProfileContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    vm: ProfileDetailViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity



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
                    .height(280.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(50.dp)
                        .padding(top = 16.dp, end = 16.dp),
                    onClick = {
                        vm.logout()
                        activity?.finish()
                        activity?.startActivity(Intent(activity, MainActivity::class.java))
                    }) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                if (vm.stateProfile.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center),
                        model = vm.stateProfile.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.user_image),
                        contentDescription = ""
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, top = 25.dp)
            ) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_person),
                        contentDescription = "",
                        tint = Purple40
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Nombre", color = Color.Gray)
                }
                Text(
                    modifier = Modifier.padding(start = 34.dp),
                    text = vm.stateProfile.name,
                    color = Purple40
                )
                Spacer(modifier = Modifier.height(28.dp))

                Row {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "",
                        tint = Purple40
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Apellido", color = Color.Gray)
                }
                Text(
                    modifier = Modifier.padding(start = 34.dp),
                    text = vm.stateProfile.lastName,
                    color = Purple40
                )
                Spacer(modifier = Modifier.height(28.dp))

                Row {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "",
                        tint = Purple40
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Email", color = Color.Gray)
                }
                Text(
                    modifier = Modifier.padding(start = 34.dp),
                    text = vm.stateProfile.email,
                    color = Purple40
                )
                Spacer(modifier = Modifier.height(28.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(modifier = Modifier
                    .height(50.dp)
                    .width(250.dp),
                    onClick = {
                        navController.navigate(route = "${Graph.MAIN_PROFILE}/${vm.stateProfile.ToUserModel().toJson()}")
                    }) {
                    Text(text = "ACTUALIZAR")
                }
            }
        }
    }
}