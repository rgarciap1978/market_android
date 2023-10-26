package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke,
    cardElevationDp: Dp,
    colorContainer: Color,
    roundedCornerShape: Dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        border = borderStroke,
        elevation = CardDefaults.cardElevation(
            defaultElevation = cardElevationDp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorContainer
        ),
        shape = RoundedCornerShape(roundedCornerShape),
        modifier = modifier
    ) {
        content()
    }
}