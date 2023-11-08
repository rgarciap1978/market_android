package com.mitocode.marketcomposeapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mitocode.marketcomposeapp.BuildConfig
import com.mitocode.marketcomposeapp.core.BasicInterceptor
import com.mitocode.marketcomposeapp.localdb.AppDatabase
import com.mitocode.marketcomposeapp.localdb.dao.ICategoryDAO
import com.mitocode.marketcomposeapp.repositories.CategoryRepository
import com.mitocode.marketcomposeapp.repositories.ProductRepository
import com.mitocode.marketcomposeapp.repositories.UserRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.IProductRepository
import com.mitocode.marketcomposeapp.repositories.interfaces.IUserRepository
import com.mitocode.marketcomposeapp.services.IService
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
    @Singleton
    fun provideBasicInterceptor(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(BasicInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): IService {
        return Retrofit
            .Builder()
            //.baseUrl(Constants.URL_BASE)
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
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