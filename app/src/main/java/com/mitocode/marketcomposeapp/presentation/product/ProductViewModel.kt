package com.mitocode.marketcomposeapp.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.interfaces.IProductRepository
import com.mitocode.marketcomposeapp.domain.models.Product
import com.mitocode.marketcomposeapp.domain.states.GenericListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val repository: IProductRepository
) : ViewModel() {

    private var state by mutableStateOf(GenericListState<Product>())

    fun getProducts(id: String) {

        viewModelScope.launch {
            repository.findById(id).onEach {
                when (it) {
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = it.message
                        )
                    }

                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Result.Successful -> {
                        state = state.copy(
                            isLoading = false,
                            data = it.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}