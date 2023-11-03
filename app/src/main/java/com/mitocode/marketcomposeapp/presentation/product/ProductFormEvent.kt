package com.mitocode.marketcomposeapp.presentation.product

sealed class ProductFormEvent {
    data class populateProducts(val uuid: String) : ProductFormEvent()
}
