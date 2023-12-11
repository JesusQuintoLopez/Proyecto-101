package com.chunmaru.app101.views.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun algo(){
    ConstraintLayout(modifier = Modifier.size(200.dp)) {
        val (redBox, blueBox, yellowBox, text) = createRefs()
        val topGuideline = createGuidelineFromTop(0.1f)


        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
            })

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red.copy(alpha = 0.4f))
            .constrainAs(redBox) {},)

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                bottom.linkTo(blueBox.bottom)
                start.linkTo(blueBox.end, 20.dp)
            })

        Text("Hello World", modifier = Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            start.linkTo(yellowBox.start)
        })

    }
}