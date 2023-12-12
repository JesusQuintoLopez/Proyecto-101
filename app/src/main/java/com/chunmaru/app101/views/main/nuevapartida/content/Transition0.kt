package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.app101.R
import com.chunmaru.app101.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transition0(
    modifier: Modifier,
    numJug: String,
    onValue: (it: String) -> Unit,
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
            Text(
                text = "Registre el numero de jugadores",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                OutlinedTextField(modifier = Modifier.weight(1f),
                    value = numJug,
                    onValueChange = { onValue(it) },
                    label = {
                        Text(
                            text = "Numero de jugadores"
                        )
                    })
                Spacer(modifier = Modifier.width(16.dp))

                IconButton(modifier = Modifier.size(50.dp).weight(0.2f), onClick = { onValueBtn() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                        contentDescription = "",
                        tint = Red,
                        modifier = Modifier.size(50.dp),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

    }
}
