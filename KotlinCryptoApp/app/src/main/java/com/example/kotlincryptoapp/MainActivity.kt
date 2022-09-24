package com.example.kotlincryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlincryptoapp.View.CryptoDetailScreen
import com.example.kotlincryptoapp.View.CryptoListScreen
import com.example.kotlincryptoapp.ui.theme.KotlinCryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  //hilte nereden başlayacağını belirtiyoruz bu şekilde.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCryptoAppTheme {
                //composelardan birinde değişme olursa tüm ekranı recompose (ekranı yeniliyor) ediyor.
                //oluşturduğumuz değişkenler tekrardan initalize edilmesin diye "remember" keywordu kullanılır.
                val navController= rememberNavController()

                //startDestination=hangi ekranla başlayacağı gösterilir.
                NavHost(navController = navController, startDestination = "crypto_list_screen"){
                 //route=hangi id'ye gidileceği.

                   //ilk ekran, burada id sini verdik sonradan oluşturup eklicez
                    composable("crypto_list_screen"){
                       CryptoListScreen(navController = navController)
                    }

                    //detail ekranını oluşturduk.crypto detali liste gidicez. id ve price ı götürcez.
                    composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoId"){
                            type= NavType.StringType
                        },
                        navArgument("cryptoPrice"){
                            type= NavType.StringType
                        }


                    )){
                        val cryptoId=remember{
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice=remember{
                            it.arguments?.getString("cryptoPrice")
                        }
                        CryptoDetailScreen(
                            id = cryptoId?:"",
                            price = cryptoPrice?:"",
                            navController = navController)

                    }
                }

                }
            }
        }
    }

