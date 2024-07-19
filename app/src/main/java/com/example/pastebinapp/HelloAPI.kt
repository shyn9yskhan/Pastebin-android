package com.example.pastebinapp

import retrofit2.Response
import retrofit2.http.GET

interface HelloAPI {
    @GET("hello")
    suspend fun getResponse() : Response<HelloResponse>
}