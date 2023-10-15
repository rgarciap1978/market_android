package com.mitocode.marketcomposeapp.domain.states

data class GenericState<T> (
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: T? = null
)