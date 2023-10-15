package com.mitocode.marketcomposeapp.presentation.category

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoryScreen() {
    Box {
        Text(text = "Categoría")
    }
}

@Preview(showSystemUi = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}