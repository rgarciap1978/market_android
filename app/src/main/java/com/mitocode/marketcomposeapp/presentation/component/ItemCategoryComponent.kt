package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.ui.theme.Black

@Composable
fun ItemCategoryComponent(
    modifier: Modifier = Modifier,
    data: Category,
    onClick: (Category) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClick(data)
            },
        border = BorderStroke(1.dp, Black),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.cover)
                    .crossfade(2000)
                    .build(),
                contentDescription = data.name,
                contentScale = ContentScale.Crop
            )
        }
    }
}