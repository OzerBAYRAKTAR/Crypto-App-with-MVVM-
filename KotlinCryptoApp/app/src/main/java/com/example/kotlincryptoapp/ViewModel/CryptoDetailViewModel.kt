package com.example.kotlincryptoapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.kotlincryptoapp.Util.Resource
import com.example.kotlincryptoapp.model.Crypto
import com.example.kotlincryptoapp.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) :ViewModel() {



    suspend fun getCrypto(id: String): Resource<Crypto>{
        return repository.getCrypto()
    }
}