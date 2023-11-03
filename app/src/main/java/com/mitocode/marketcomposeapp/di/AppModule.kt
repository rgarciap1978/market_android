package com.mitocode.marketcomposeapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mitocode.marketcomposeapp.localdb.AppDatabase
import com.mitocode.marketcomposeapp.localdb.dao.ICategoryDAO
import com.mitocode.marketcomposeapp.repositories.CategoryRepository
import com.mitocode.marketcomposeapp.repositories.ProductRepository
import com.mitocode.marketcomposeapp.repositories.UserRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.IProductRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.IUserRepository
import com.mitocode.marketcomposeapp.services.IService
import com.mitocode.marketcomposeapp.services.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharePreferences(@ApplicationContext context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "user-data",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideIUserRepository(
        sharedPreferences: SharedPreferences,
        service: IService
    ): IUserRepository {
        return UserRepository(sharedPreferences, service)
    }

    @Provides
    @Singleton
    fun provideICategoryRepository(
        sharedPreferences: SharedPreferences,
        service: IService,
        dao: ICategoryDAO
    ): ICategoryRepository {
        return CategoryRepository(
            sharedPreferences,
            service,
            dao
        )
    }

    @Provides
    @Singleton
    fun provideIProductRepository(
        service: IService
    ): IProductRepository {
        return ProductRepository(service)
    }

    @Provides
    fun provideRetrofit(): IService {
        val url = "http://35.169.242.154:3000/"
        val token = TokenManager.getToken()

        var client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        name = "dbMarket"
    ).build()

    @Provides
    @Singleton
    fun provideDAO(
        appDatabase: AppDatabase
    ): ICategoryDAO = appDatabase.categoryDAO()
}