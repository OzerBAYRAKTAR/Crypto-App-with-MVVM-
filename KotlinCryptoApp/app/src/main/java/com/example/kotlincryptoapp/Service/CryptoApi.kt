package com.example.kotlincryptoapp.Service

import com.example.kotlincryptoapp.model.Crypto
import com.example.kotlincryptoapp.model.CryptoList
import retrofit2.http.GET

interface CryptoApi {



    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getCryptoList(API_KEY: String):CryptoList


    @GET("https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    fun getCrypto(API_KEY: String):  Crypto

}