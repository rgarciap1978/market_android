package com.mitocode.marketcomposeapp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.mitocode.marketcomposeapp.util.DefaultPreview

@Composable
fun SplashScreen(
    onFinished: ()->Unit
) {
    val degrees = remember {
        Animatable(0f)
    }

    LaunchedEffect(
        key1 = true
    ) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 3000,
                delayMillis = 1000
            )
        )

        onFinished()
    }

    ImageSplash(degrees = degrees.value)
}

@DefaultPreview
@Composable
fun SplashScreenPreview() {
    SplashScreen(){}
}
