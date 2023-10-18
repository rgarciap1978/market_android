package com.mitocode.marketcomposeapp.presentation.locate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LocateScreen(padding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
        Text(text = "Location")
    }
}