package com.mitocode.marketcomposeapp.data.repositories

import android.content.SharedPreferences
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.domain.mappers.toList
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.services.IService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    val sharedPreferences: SharedPreferences,
    val service: IService
) : ICategoryRepository {

    override suspend fun getAll(): Flow<Result<List<Category>>> = flow {
        try {
            emit(Result.Loading())
            val response = service.getAll()
            if (response.success) {
                emit(Result.Successful(data = response.data.toList()))
            } else {
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud: ${ex.message()}"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
        }
    }
}