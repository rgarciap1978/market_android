package com.mitocode.marketcomposeapp.data.responses

import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO

data class CategoryResponse(
    val success: Boolean,
    val message: String,
    val data: List<CategoryDTO>
)
