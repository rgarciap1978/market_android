package com.mitocode.marketcomposeapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    val uuid: String,
    @SerializedName("descripcion")
    val description: String,
    @SerializedName("codigo")
    val code: String,
    @SerializedName("caracteristicas")
    val feature: String,
    @SerializedName("precio")
    val price: Int,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("imagenes")
    val images: List<String>
)
