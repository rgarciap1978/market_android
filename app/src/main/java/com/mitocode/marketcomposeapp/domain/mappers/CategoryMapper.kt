package com.mitocode.marketcomposeapp.domain.mappers

import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import com.mitocode.marketcomposeapp.domain.models.Category

fun List<CategoryDTO>.toListCategory() : List<Category> = map {
    Category(
        uuid = it.uuid,
        name = it.nombre,
        cover = it.cover
    )
}