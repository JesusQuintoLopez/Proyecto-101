package com.chunmaru.app101.views.main.nuevapartida.content


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.app101.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemName(name: String) {
    Column {
        Text(
            text = if (name.length > 6) name.substring(0, 6) else name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

    }
}