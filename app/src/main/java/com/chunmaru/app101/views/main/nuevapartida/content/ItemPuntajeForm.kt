package com.chunmaru.app101.views.main.nuevapartida.content

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemPuntajeForm(numJug:String,value1:String,value2:String,value3:String,value4:String){

    if(numJug.equals("1") || numJug.equals("2") || numJug.equals("3") || numJug.equals("4")){
        OutlinedTextField(
            modifier = Modifier.width(60.dp),
            value = "value",
            onValueChange = {},
            textStyle = TextStyle(fontSize = 16.sp),
            label = { Text(text = "Ptje", fontSize = 12.sp) },
            placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
        )
    }

    if(numJug.equals("2") || numJug.equals("3") || numJug.equals("4")){
        OutlinedTextField(
            modifier = Modifier.width(60.dp),
            value = "value",
            onValueChange = {},
            textStyle = TextStyle(fontSize = 16.sp),
            label = { Text(text = "Ptje", fontSize = 12.sp) },
            placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
        )
    }

    if( numJug.equals("3") || numJug.equals("4")){
        OutlinedTextField(
            modifier = Modifier.width(60.dp),
            value = "value",
            onValueChange = {},
            textStyle = TextStyle(fontSize = 16.sp),
            label = { Text(text = "Ptje", fontSize = 12.sp) },
            placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
        )
    }

    if(numJug.equals("4")){
        OutlinedTextField(
            modifier = Modifier.width(60.dp),
            value = "value",
            onValueChange = {},
            textStyle = TextStyle(fontSize = 16.sp),
            label = { Text(text = "Ptje", fontSize = 12.sp) },
            placeholder = { Text(text = "Ptj", fontSize = 12.sp) }
        )
    }


}