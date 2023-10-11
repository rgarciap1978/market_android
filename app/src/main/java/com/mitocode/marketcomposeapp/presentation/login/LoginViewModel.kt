package com.mitocode.marketcomposeapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.model.LoginRequest
import com.mitocode.marketcomposeapp.data.repository.UserRepository
import com.mitocode.marketcomposeapp.domain.repository.IUserPrepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // STATE SCREEN
    var state by mutableStateOf(LoginState())

    // STATE UI
    var stateElements by mutableStateOf(LoginElements())

    // EVENTS
    fun onEvent(event: LoginFormEvent){
        when(event){
            is LoginFormEvent.EmailChange -> {
                TODO()
            }
            is LoginFormEvent.PasswordChange -> {
                TODO()
            }
            LoginFormEvent.Submit -> {
                singIn()
            }
            is LoginFormEvent.VisualTransformationChange -> {
                TODO()
            }
        }
    }

    private fun singIn() {

        val userRepository:IUserPrepository = UserRepository()
        viewModelScope.launch {
            userRepository
                .signIn(LoginRequest("jledesma2509@gmail.com", "12345"))
                .onEach {
                    when(it){
                        is Result.Error -> {
                            TODO()
                        }
                        is Result.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                        is Result.Successfull -> {
                            state = state.copy(successfull = it.data, isLoading = false)
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }
}