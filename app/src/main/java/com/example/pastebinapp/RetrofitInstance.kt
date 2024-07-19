package com.example.pastebinapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : HelloAPI by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.105:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HelloAPI::class.java)
    }
}