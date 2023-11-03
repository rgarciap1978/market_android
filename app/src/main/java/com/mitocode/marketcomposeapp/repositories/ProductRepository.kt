package com.mitocode.marketcomposeapp.repositories

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.mappers.toList
import com.mitocode.marketcomposeapp.domain.models.Product
import com.mitocode.marketcomposeapp.repositories.interfaces.IProductRepository
import com.mitocode.marketcomposeapp.services.IService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepository @Inject constructor(
    val service: IService
) : IProductRepository {
    override suspend fun findById(uuid: String): Flow<Result<List<Product>>> = flow {
        try {
            emit(Result.Loading())
            val response = service.findById(uuid)
            if (response.success) {
                emit(Result.Successful(data = response.data.toList()))
            } else {
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
        }
    }

}