package com.example.movieapp.ui.movieDetail

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (
        var id: Int,

        @SerializedName("backdrop_path") var backdropPath: String?,
        @SerializedName("overview") var overview: String,
        @SerializedName("poster_path") var posterPath: String?,
        @SerializedName("release_date") var releaseDate: String,
        @SerializedName("title") var title: String,

        var videos:VideosList
)
