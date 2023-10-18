package com.mitocode.marketcomposeapp.domain.states

data class GenericListState<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<T>? = null,
    val isEmpty: Boolean = false
)
