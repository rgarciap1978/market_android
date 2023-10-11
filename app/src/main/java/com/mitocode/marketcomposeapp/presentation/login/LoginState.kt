package com.mitocode.marketcomposeapp.presentation.login

import com.mitocode.marketcomposeapp.data.model.UserDTO

data class LoginState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val successfull: UserDTO? = null
)