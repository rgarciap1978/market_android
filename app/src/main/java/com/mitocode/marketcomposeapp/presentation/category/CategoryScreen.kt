package com.mitocode.marketcomposeapp.presentation.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.presentation.component.ItemCategoryComponent

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    padding: PaddingValues,
    onClick: (Category) -> Unit
) {
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Categoría",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            state.data?.let {
                items(it) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green)
                    ) {
                        ItemCategoryComponent(
                            data = it,
                            onClick = {
                                onClick(it)
                            }
                        )
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
