package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.app101.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transition1(
    modifier: Modifier,
    numJug:Int,
    jug1: String,
    jug2: String,
    jug3: String,
    jug4: String,
    apuesta: String,
    onValue1: (it: String) -> Unit,
    onValue2: (it: String) -> Unit,
    onValue3: (it: String) -> Unit,
    onValue4: (it: String) -> Unit,
    onValueApu: (it: String) -> Unit,
    onValueBtn: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = jug1,
                onValueChange = { onValue1(it) },
                label = {
                    Text(
                        text = "jugador 1"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = jug2,
                onValueChange = { onValue2(it) },
                label = {
                    Text(
                        text = "jugador 2"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = jug3,
                onValueChange = { onValue3(it) },
                label = {
                    Text(
                        text = "jugador 3"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = jug4,
                onValueChange = { onValue4(it) },
                label = {
                    Text(
                        text = "jugador 4"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = apuesta,
                onValueChange = { onValueApu(it) },
                label = {
                    Text(
                        text = "Apuesta S/"
                    )
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.height(50.dp).width(250.dp),
                onClick = { onValueBtn() },
                colors = ButtonDefaults.buttonColors(containerColor = Red)
            ) {
                Text(text = "Iniciar Partida", color = Color.White)
            }
        }

    }
}