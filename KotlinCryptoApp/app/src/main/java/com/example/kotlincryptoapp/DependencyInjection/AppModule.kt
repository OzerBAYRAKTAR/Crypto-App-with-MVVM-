package com.example.kotlincryptoapp.DependencyInjection

import com.example.kotlincryptoapp.Service.CryptoApi
import com.example.kotlincryptoapp.Util.Constants.BASE_URL
import com.example.kotlincryptoapp.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//retrofit,repostitoy vs başka yerlere inject edilmek üzere provide ettiğimiz sınıf.


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCryptoRepository(
        api:CryptoApi
    )=CryptoRepository(api)

    @Singleton
    @Provides
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CryptoApi::class.java)
    }



}