package com.example.movieapp.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.network.APIClient
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieRemoteDataSource

class MovieDetailViewModel:ViewModel() {
    val currentMovieId=MutableLiveData<Int>()
    var movie:MovieDetails?=null
    private val movieRemoteDataSource = MovieRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails(): MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }

}