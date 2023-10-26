package com.mitocode.marketcomposeapp.data.dtos

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    val uuid: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("cover")
    val cover: String
)