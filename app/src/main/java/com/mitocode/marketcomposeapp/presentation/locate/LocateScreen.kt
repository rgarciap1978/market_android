package com.mitocode.marketcomposeapp.presentation.locate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mitocode.marketcomposeapp.presentation.component.GoogleMapComponent

@Composable
fun LocateScreen(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        GoogleMapComponent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}