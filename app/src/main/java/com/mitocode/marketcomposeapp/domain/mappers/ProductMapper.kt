package com.mitocode.marketcomposeapp.domain.mappers

import com.mitocode.marketcomposeapp.data.dto.ProductDTO
import com.mitocode.marketcomposeapp.domain.models.Product

fun List<ProductDTO>.toList(): List<Product> = map {
    Product(
        uuid = it.uuid,
        description = it.description,
        code = it.code,
        feature = it.feature,
        price = it.price,
        stock = it.stock,
        images = it.images
    )
}