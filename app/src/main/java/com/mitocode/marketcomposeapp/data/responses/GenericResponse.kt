package com.mitocode.marketcomposeapp.data.responses

data class GenericResponse<T> (
    val success: Boolean,
    val message: String,
    val data: T?,
    val token: String
)