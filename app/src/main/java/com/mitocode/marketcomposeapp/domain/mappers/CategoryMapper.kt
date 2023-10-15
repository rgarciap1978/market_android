package com.mitocode.marketcomposeapp.domain.mappers

import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import com.mitocode.marketcomposeapp.domain.models.Category

fun CategoryDTO.toCategory() : Category {
    return Category(
        uuid = uuid,
        name = nombre,
        cover = cover
    )
}