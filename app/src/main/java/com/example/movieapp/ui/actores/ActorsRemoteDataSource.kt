package com.example.movieapp.ui.actores

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constant
import retrofit2.Retrofit

class ActorsRemoteDataSource(retrofit: Retrofit) {
    private val apiService: ActorsApiService = retrofit.create(ActorsApiService::class.java)
    private val actorsMapper = ActorsMapper()
    fun getActors() : List<Actor> {
        return apiService.getActors(Constant.API_KEY, Constant.LANGUAGE)
            .executeAndDeliver()
            .actors
            .map { actorsMapper.map(it) }
    }
}