package com.mitocode.marketcomposeapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.marketcomposeapp.ui.theme.Black
import com.mitocode.marketcomposeapp.ui.theme.white

@Composable
fun BasicImageComponent (modifier: Modifier = Modifier, @DrawableRes image: Int, description: String) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier
    )
}

@Composable
fun RoundedButtonComponent(modifier: Modifier = Modifier,
                           title: String,
                           onClick: ()->Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Black
        ),
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Black),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = white)
    }
}