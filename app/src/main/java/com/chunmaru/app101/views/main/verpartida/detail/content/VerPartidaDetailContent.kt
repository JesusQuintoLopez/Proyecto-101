package com.chunmaru.app101.views.main.verpartida.detail.content

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.ui.theme.Purple40
import com.chunmaru.app101.ui.theme.Red
import com.chunmaru.app101.views.main.verpartida.detail.VerPartidaDetailViewModel

@Composable
fun VerPartidaDetailContent(jugadores:List<JugadorEntity>) {

    Box(
        modifier = Modifier
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

        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(25.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "DETALLES DE LA PARTIDA",color = Purple40, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Ptj:", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "NElim:", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Estado:", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Deuda:", fontSize = 12.sp, color = Purple40, fontWeight = FontWeight.Bold)
                    }
                    LazyRow(){
                        items(jugadores){jug->
                            Column(Modifier.padding(start = 16.dp)) {
                                Text(text = jug.name, fontSize = 12.sp, color = Red, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = jug.puntaje.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = jug.numElim.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = jug.estado.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = jug.deuda.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                }
            }
        }

    }

}