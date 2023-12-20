package com.chunmaru.app101.views.main.verpartida.list.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chunmaru.app101.model.PartidaResponse
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.ui.theme.Red

@Composable
fun ItemPartida(modifier: Modifier,partida:PartidaResponse){
    Row(modifier = modifier) {
        Column(Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
            Row() {
                Text(text = "Anfitrion:", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = partida.email, color = Red)
            }
            Row() {
                Text(text = "Num de jugadores:", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = partida.numJug.toString(), color = Color.White)
            }
            Row() {
                Text(text = "Apuesta:", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = partida.apuesta.toString(), color = Color.White)
            }
            
        }
    }
}