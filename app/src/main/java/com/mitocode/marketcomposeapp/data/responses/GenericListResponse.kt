package com.mitocode.marketcomposeapp.data.responses

data class GenericListResponse<T>(
    val success: Boolean,
    val message: String,
    val data: List<T>
)
