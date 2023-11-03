package com.mitocode.marketcomposeapp.presentation.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitocode.marketcomposeapp.presentation.component.ItemProductComponent

@Composable
fun ProductScreen(
    uuid: String,
    viewModel: ProductViewModel = hiltViewModel(),
    padding: PaddingValues
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ProductFormEvent.populateProducts(uuid))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            columns = GridCells.Fixed(2)
        ) {
            state.data?.let {
                items(it) {
                    ItemProductComponent(
                        data = it,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }

}