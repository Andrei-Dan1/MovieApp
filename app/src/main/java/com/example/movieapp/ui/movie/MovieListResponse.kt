package com.example.movieapp.ui.movie


import com.google.gson.annotations.SerializedName

class MovieListResponse(
    @SerializedName("results")
    val movies: List<MovieResponse>) {
}