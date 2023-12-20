package com.chunmaru.app101.views.main.verpartida.list.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chunmaru.app101.R
import com.chunmaru.app101.model.PartidaResponse
import com.chunmaru.app101.navigation.screen.main.MainUnirseDetail
import com.chunmaru.app101.ui.theme.Purple40

@Composable
fun VerPartidaListContent(navController: NavHostController,partidas:List<PartidaResponse>) {
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
        Card(modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp, start = 25.dp, end = 25.dp, bottom = 100.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "PARTIDAS DEL DIA",
                    color = Purple40,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)){
                items(partidas){partida->
                    ItemPartida(modifier = Modifier
                        .clip(shape = RoundedCornerShape(25.dp))
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
                        .background(color = Purple40)
                        .clickable { navController.navigate(MainUnirseDetail.unirseDetail.passUID(partida.idDoc)) }
                        ,partida = partida
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}