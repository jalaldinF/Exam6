package com.example.exam6modul.networking.servise

import com.example.exam6modul.model.Card
import retrofit2.Call
import retrofit2.http.*


interface UserServise {

    @GET("cards")
    fun getAllCards(): Call<ArrayList<Card>>

    @GET("cards/{id}")
    fun getCard(@Path("id")id:Int):Call<Card>

    @POST("cards")
    fun createCards(@Body card: Card):Call<Card>

    @PUT("cards/{id}")
     fun updateCard(@Path("id")id:Int,@Body card: Card):Call<Card>

     @DELETE("cards/id{id}")
     fun deleteCard(@Path("id")id:Int):Call<Card>

}