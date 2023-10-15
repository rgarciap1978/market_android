package com.mitocode.marketcomposeapp.domain.models

data class User (
    val uuid: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val gender: String,
    val document: String,
    val type: String
)