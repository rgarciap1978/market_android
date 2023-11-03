package com.mitocode.marketcomposeapp.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.domain.states.GenericListState
import com.mitocode.marketcomposeapp.repositories.interfaces.ICategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repository: ICategoryRepository
) : ViewModel() {

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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.sync()
            }

            repository.getCategories.catch {
                state = state.copy(isLoading = false, error = it.message)
            }.collect {
                state = state.copy(isLoading = false, data = it)
            }
        }

    }
}