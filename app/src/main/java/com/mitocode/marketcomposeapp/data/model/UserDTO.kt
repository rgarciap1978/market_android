package com.mitocode.marketcomposeapp.data.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val uuid: String,

    @SerializedName("nombres")
    val Firstname: String,

    @SerializedName("apellidos")
    val Lastname: String,

    val email: String,

    @SerializedName("celular")
    val Phone: String,
    val genero: String,
    val nroDoc: String,
    val tipo: String
)