package com.mitocode.marketcomposeapp.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.CategoryRepository
import com.mitocode.marketcomposeapp.data.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.domain.states.GenericListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    var state by mutableStateOf(GenericListState<Category>())

    init {
        onEvent(CategoryFromEvent.PopulateCategories)
    }
    private fun onEvent(event: CategoryFromEvent) {
        when (event) {
            CategoryFromEvent.PopulateCategories -> {
                populateCategories()
            }
        }
    }

    private fun populateCategories() {
        val repository: ICategoryRepository = CategoryRepository()
        viewModelScope.launch {
            repository.populateCategories().onEach {

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