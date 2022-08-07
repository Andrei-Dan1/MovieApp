package com.example.movieapp.ui.movie

import com.example.movieapp.ui.movieDetail.MovieDetailResponse
import com.example.movieapp.ui.movieDetail.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("discover/movie")
    fun discoverMovie(@Query("api_key") apiKey : String,
                      @Query("language") language : String
    ): Call<MovieListResponse>  //Asta pentru popular now

    @GET("discover/movie")
    fun moviePreferance(@Query("api_key") apiKey : String,
                        @Query("language") language : String,
                        @Query("with_cast") withCast: String,
                        @Query("with_genres") withGenres: String

    ): Call<MovieListResponse>
    @GET("search/movie")
    fun getSearchMovie
                (
              @Query("api_key") apiKey : String,
              @Query("language") language : String,
              @Query("query") query: String
    ): Call<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("append_to_response") appendToResponse: String,

    ): Call<MovieDetailResponse>

    @GET("movie/popular")
    fun getRandomMovies(
           @Query("api_key") apiKey : String,
           @Query("language") language : String,
           @Query("page") page: Int
    ): Call<MovieListResponse>
}