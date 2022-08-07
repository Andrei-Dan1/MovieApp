package com.example.movieapp.ui.movie

import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresResponse
import retrofit2.http.Query

class MovieMapper {
    fun map(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            description = movieResponse.description,
            image=movieResponse.image,
            date=movieResponse.date,
            isFavorite=false,
            isWatched = false,
        )

    }
}