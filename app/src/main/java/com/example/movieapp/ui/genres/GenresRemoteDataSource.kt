package com.example.movieapp.ui.genres

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constant
import retrofit2.Retrofit


class GenresRemoteDataSource(retrofit: Retrofit) {
    private val apiService: GenresApiService = retrofit.create(GenresApiService::class.java)
    private val genreMapper = GenresMapper()
    fun getGenres() : List<Genre> {
        return apiService.getGenres(Constant.API_KEY, Constant.LANGUAGE)
            .executeAndDeliver()
            .genres
            .map { genreMapper.map(it) }
    }
}