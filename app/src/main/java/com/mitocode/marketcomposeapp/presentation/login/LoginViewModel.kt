package com.mitocode.marketcomposeapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.UserRepository
import com.mitocode.marketcomposeapp.data.repositories.interfaces.IUserRepository
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.domain.models.User
import com.mitocode.marketcomposeapp.domain.states.GenericState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // STATE SCREEN
    var state by mutableStateOf(GenericState<User>())

    // STATE UI
    var stateElements by mutableStateOf(LoginElements())

    // EVENTS
    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChange -> {
                stateElements = stateElements.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChange -> {
                stateElements = stateElements.copy(password = event.password)
            }

            LoginFormEvent.Submit -> {
                singIn()
            }

            is LoginFormEvent.VisualTransformationChange -> {
                stateElements =
                    stateElements.copy(visualTransformation = event.visualTransformation)
            }
        }
    }

    private fun singIn() {

        val userRepository: IUserRepository = UserRepository()

        viewModelScope.launch {
            userRepository
                .signIn(LoginRequest(stateElements.email, stateElements.password))
                .onEach {
                    when (it) {
                        is Result.Error -> {
                            state =
                                state.copy(
                                    error = it.message,
                                    isLoading = false,
                                    successful = null
                                )
                        }

                        is Result.Loading -> {
                            state = state.copy(isLoading = true)
                        }

                        is Result.Successful -> {
                            state = state.copy(
                                successful = it.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun resetStateError() {
        state = state.copy(successful = null, error = null)
    }
}