package com.example.MovieApp.Network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// creating the retrofit instance to make api calls
object API {

    // configuring gson to serialize nulls in the json response
    private val gson = GsonBuilder().serializeNulls().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    // creating the ApiServices implementation using retrofit instance
    val apiServices: ApiServices = retrofit.create(ApiServices::class.java)
}