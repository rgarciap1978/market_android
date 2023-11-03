package com.mitocode.marketcomposeapp.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val uuid: String,
    @SerializedName("nombres")
    val Firstname: String,
    @SerializedName("apellidos")
    val Lastname: String,
    @SerializedName("email")
    val Email: String,
    @SerializedName("celular")
    val Phone: String,
    @SerializedName("genero")
    val Gender: String,
    @SerializedName("nroDoc")
    val Id: String,
    @SerializedName("tipo")
    val Type: String
)