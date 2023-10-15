package com.mitocode.marketcomposeapp.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import com.mitocode.marketcomposeapp.domain.states.GenericState
import com.mitocode.marketcomposeapp.presentation.login.LoginElements
import com.mitocode.marketcomposeapp.presentation.login.LoginFormEvent

class CategoryViewModel : ViewModel() {

    var state by mutableStateOf(GenericState<CategoryDTO>())

    var stateElement by mutableStateOf(LoginElements())

    fun onEvent(event: LoginFormEvent){

    }
}