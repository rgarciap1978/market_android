package com.mitocode.marketcomposeapp.domain.model

import com.mitocode.marketcomposeapp.data.model.UserDTO

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

// Mapper
fun UserDTO.toUser(): User{
    return User(
        uuid = uuid,
        firstname = Firstname,
        lastname = Lastname,
        email = email,
        phone = Phone,
        gender = genero,
        document = nroDoc,
        type = tipo
    )
}