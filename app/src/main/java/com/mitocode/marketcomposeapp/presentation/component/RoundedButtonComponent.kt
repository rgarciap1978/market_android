package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.marketcomposeapp.ui.theme.Black
import com.mitocode.marketcomposeapp.ui.theme.white

@Composable
fun RoundedButtonComponent(modifier: Modifier = Modifier,
                           title: String,
                           onClick: ()->Unit,
                           displayProgressBar: Boolean = false) {
    if(!displayProgressBar) {
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
                color = white
            )
        }
    }else{
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            strokeWidth = 4.dp
        )
    }
}