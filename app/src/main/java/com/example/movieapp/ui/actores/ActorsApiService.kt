package com.example.movieapp.ui.actores

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ActorsApiService {
    @GET("person/popular")
    fun getActors(@Query ("api_key") apiKey : String,
                  @Query ("language") language : String,
    ): Call<ActorsListResponse>
}