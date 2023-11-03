package com.mitocode.marketcomposeapp.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.models.Product
import com.mitocode.marketcomposeapp.domain.states.GenericListState
import com.mitocode.marketcomposeapp.repositories.interfaces.IProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: IProductRepository
) : ViewModel() {

    var state by mutableStateOf(GenericListState<Product>())

    fun onEvent(event: ProductFormEvent) {
        when (event) {
            is ProductFormEvent.populateProducts -> {
                getProducts(event.uuid)
            }
        }
    }

    private fun getProducts(uuid: String) {
        viewModelScope.launch {
            repository.findById(uuid).onEach {
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