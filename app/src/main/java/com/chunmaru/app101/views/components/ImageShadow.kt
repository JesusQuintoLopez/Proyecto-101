package com.chunmaru.app101.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chunmaru.app101.R

@Composable
fun ImageShadow(){
    Image(
        modifier = Modifier
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