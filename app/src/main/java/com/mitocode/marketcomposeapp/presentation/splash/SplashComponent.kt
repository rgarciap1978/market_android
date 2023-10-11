package com.mitocode.marketcomposeapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mitocode.marketcomposeapp.R
import com.mitocode.marketcomposeapp.ui.theme.PrimaryDark
import com.mitocode.marketcomposeapp.ui.theme.PrimaryLight
import com.mitocode.marketcomposeapp.ui.theme.Purple
import com.mitocode.marketcomposeapp.util.DefaultComponentLightOrDarkPreview

@Composable
fun ImageSplash(degrees: Float) {



    val backgoundColor = if (isSystemInDarkTheme()) Brush.verticalGradient(listOf(PrimaryDark, Purple))
    else  Brush.verticalGradient(listOf(PrimaryLight, Purple))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgoundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = "Logo Splash",
            modifier = Modifier
                .rotate(degrees)
                .size(180.dp)
        )
    }
}

@DefaultComponentLightOrDarkPreview
@Composable
fun ImageSplashPreview() {
    ImageSplash(0f)
}