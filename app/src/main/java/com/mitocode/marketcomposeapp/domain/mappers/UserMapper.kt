package com.mitocode.marketcomposeapp.domain.mappers

import com.mitocode.marketcomposeapp.data.dtos.UserDTO
import com.mitocode.marketcomposeapp.domain.models.User

fun UserDTO.toUser(): User {
    return User(
        uuid = uuid,
        firstname = Firstname,
        lastname = Lastname,
        email = Email,
        phone = Phone,
        gender = Gender,
        document = Id,
        type = Type
    )
}