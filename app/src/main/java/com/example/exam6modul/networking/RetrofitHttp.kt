package com.example.exam6modul.networking

import com.example.exam6modul.networking.servise.UserServise
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {
    companion object{

        private val TAG:String = RetrofitHttp::class.java.simpleName

        const val IS_TESTER:Boolean = true

        const val SERVER_DEV = "https://6232af568364d63035c19a27.mockapi.io/api/c1/"
        const val SERVER_PRO = "https://6232af568364d63035c19a27.mockapi.io/api/c1/"

        private fun server():String{
            return if (IS_TESTER){
                SERVER_DEV
            } else{
                SERVER_PRO
            }
        }

        private fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }
        val userServise:UserServise = getRetrofit().create(UserServise::class.java)
    }
}