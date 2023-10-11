package com.mitocode.marketcomposeapp.presentation.login

sealed class LoginFormEvent {
    data class EmailChange(val email: String) : LoginFormEvent()
    data class PasswordChange(val password: String) : LoginFormEvent()
    data class VisualTransformationChange(val visualTransformationChange: Boolean) : LoginFormEvent()

    object Submit  : LoginFormEvent()
}
