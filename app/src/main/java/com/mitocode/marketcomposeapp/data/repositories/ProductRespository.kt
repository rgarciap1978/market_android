package com.mitocode.marketcomposeapp.data.repositories

import android.content.SharedPreferences
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.interfaces.IProductRepository
import com.mitocode.marketcomposeapp.domain.models.Product
import com.mitocode.marketcomposeapp.services.IService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRespository @Inject constructor(
    val sharedPreferences: SharedPreferences,
    val service: IService
) : IProductRepository {
    override suspend fun findById(id: String): Flow<Result<List<Product>>> = flow {
        /*try {
            emit(Result.Loading())
            val response = service.findById(id)
            if (response.success) {
                var data = response.data
            } else {
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
        }*/
    }

}