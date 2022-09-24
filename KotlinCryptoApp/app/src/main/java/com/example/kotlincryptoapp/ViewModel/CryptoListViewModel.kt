package com.example.kotlincryptoapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincryptoapp.Util.Resource
import com.example.kotlincryptoapp.model.CryptoListItem
import com.example.kotlincryptoapp.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.annotation.meta.When
import javax.inject.Inject


@HiltViewModel  //hilt yazarsak direkt @inject constructor yazabilirz
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository

):ViewModel() {    //viewmodelda livedataları yazarız(veya flowlar) fakat burda composedaki mutablestate var.Statede değişim olursa
    //ekrana bunu yansıtabililirz. o yüzden livedataya gerek yok.composeda mutable state kullanılır.


    var cryptoList= mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)

    private var initialCryptoList= listOf<CryptoListItem>()
    private var isSearchStarting=true  //arama başlıyor mu diye kontrol.


    init {
        loadCryptos()
    }

    fun searchCryptoList(query:String){
        val listToSearch=if (isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch (Dispatchers.Default){
            if (query.isEmpty()){
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }else{
                val results=listToSearch.filter {
                    it.currency.contains(query.trim(),ignoreCase = true)
                }


                if (isSearchStarting){
                    initialCryptoList=cryptoList.value  //arama ilk defa başlıyorsa, daha önceden download edilen veriyi intialiliste aktarılıyor.
                    isSearchStarting=false
                }
            }



        }
    }


    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()
            when(result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, item ->
                        CryptoListItem(item.currency,item.price)
                    } as List<CryptoListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> TODO()
            }
        }
    }
}


