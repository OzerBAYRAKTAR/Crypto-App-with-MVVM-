package com.example.kotlincryptoapp.repository

import com.example.kotlincryptoapp.Service.CryptoApi
import com.example.kotlincryptoapp.Util.Constants.API_KEY
import com.example.kotlincryptoapp.Util.Resource
import com.example.kotlincryptoapp.model.Crypto
import com.example.kotlincryptoapp.model.CryptoList
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api:CryptoApi
){
    suspend fun getCryptoList():Resource<CryptoList>{
        val response=try{
            println("başarılı")
            api.getCryptoList(API_KEY)

        }catch (e:Exception){
            return Resource.Error("api error verdi")

        }
        return Resource.Success(response)

    }
    suspend fun getCrypto():Resource<Crypto>{
        val response=try {
            api.getCrypto(API_KEY)
        }catch (e:Exception){
            return Resource.Error("ERRORrrr")
        }
        return Resource.Success(response)
    }
}