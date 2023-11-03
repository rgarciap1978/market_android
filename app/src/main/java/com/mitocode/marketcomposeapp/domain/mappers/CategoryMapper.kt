package com.mitocode.marketcomposeapp.domain.mappers

import com.mitocode.marketcomposeapp.data.dto.CategoryDTO
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.localdb.entities.CategoryEntity

fun List<CategoryDTO>.DtoToDomain() : List<Category> = map {
    Category(
        uuid = it.uuid,
        name = it.name,
        cover = it.cover
    )
}

fun List<Category>.toEntity() : List<CategoryEntity> = map {
    CategoryEntity(
        uuid = it.uuid,
        name = it.name,
        cover = it.cover,
        status = "S"
    )
}

fun List<CategoryEntity>.EntityToDomain() : List<Category> = map {
    Category(
        uuid = it.uuid,
        name = it.name,
        cover = it.cover
    )
}