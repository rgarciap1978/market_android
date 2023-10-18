package com.mitocode.marketcomposeapp.core

sealed class Result<T>(val data:T? = null, val message: String? = null){

    class Successful<T>(data: T?) : Result<T>(data)

    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)

    class Loading<T>(data: T? = null) : Result<T>(data)
}
