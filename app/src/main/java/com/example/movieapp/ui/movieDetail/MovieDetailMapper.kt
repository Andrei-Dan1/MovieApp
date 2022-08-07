package com.example.movieapp.ui.movieDetail

import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieResponse

class MovieDetailMapper {
    fun map(movieResponse: MovieDetailResponse): MovieDetails {
        return MovieDetails(
            id = movieResponse.id,
            title = movieResponse.title,
            backdropPath = movieResponse.backdropPath,
            overview = movieResponse.overview ,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            videos=movieResponse.videos
            )

    }
}