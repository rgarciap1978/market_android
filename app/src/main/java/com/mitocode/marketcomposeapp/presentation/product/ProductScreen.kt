package com.mitocode.marketcomposeapp.presentation.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mitocode.marketcomposeapp.presentation.component.ItemProductComponent

@Composable
fun ProductScreen(
    uuid: String,
    paddingValues: PaddingValues
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(10) {
                ItemProductComponent(
                    number = it,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }

}