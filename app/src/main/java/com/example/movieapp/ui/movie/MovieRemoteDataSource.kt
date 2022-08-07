package com.example.movieapp.ui.movie

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.ui.movieDetail.MovieDetailMapper
import com.example.movieapp.ui.movieDetail.MovieDetails
import com.example.movieapp.utils.Constant
import retrofit2.Retrofit


class MovieRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MovieApiService = retrofit.create(MovieApiService::class.java)
    private val movieMapper = MovieMapper()

    private val movieDetailMapper = MovieDetailMapper()

    fun getMovie(): List<Movie> {
        return apiService.discoverMovie(Constant.API_KEY, Constant.LANGUAGE,)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getPreference(withCast: String, withGenres: String): List<Movie> {
        return apiService.moviePreferance(Constant.API_KEY, Constant.LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getSearchedMovies(query: String): List<Movie> {
        return apiService.getSearchMovie(Constant.API_KEY, Constant.LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getRandomMovie(page: Int): List<Movie> {
        return apiService.getRandomMovies(Constant.API_KEY, Constant.LANGUAGE, page)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }
    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId,Constant.API_KEY, Constant.LANGUAGE,Constant.APPEND_TO_RESPONSE)
            .executeAndDeliver()
            .let { movieDetailMapper.map(it) }
    }
}