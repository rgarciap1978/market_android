package com.mitocode.marketcomposeapp.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun BasicImageComponent(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    description: String,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier,
        contentScale = contentScale
    )
}
