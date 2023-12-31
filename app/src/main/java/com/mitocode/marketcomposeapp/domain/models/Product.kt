package com.mitocode.marketcomposeapp.domain.models

data class Product(
    val uuid: String,
    val description: String,
    val code: String,
    val feature: String,
    val price: Double,
    val stock: Int,
    val images: List<String>
)
