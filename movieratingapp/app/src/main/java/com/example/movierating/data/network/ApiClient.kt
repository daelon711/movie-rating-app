//gets base url and converts api
//holds retrofit setup
//sets api base url and attaches to json converter
//exposes 1 val api: movieapiservice

package com.example.movierating.data.network


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private val okHttp = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/") // TODO change
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()

    val apiService: MovieApiService = retrofit.create(MovieApiService::class.java) // returns created api service that can use movieapiservice classes with get and post
}


