package com.example.movieapp.ui.movie

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresLocalDataSource
import com.example.movieapp.ui.genres.GenresRemoteDataSource
import com.example.movieapp.ui.genres.GenresRepository
import retrofit2.http.Query

class MovieRepository {
    companion object {
        val instance = MovieRepository()

    }
    private val moviesRemoteDataSource= MovieRemoteDataSource(APIClient.instance.retrofit)
    private val moviesLocalDataSource= MovieLocalDataSource(Database.instance)

    fun getPreference() = moviesRemoteDataSource.getMovie()
    fun getAllRemote(page:Int) =moviesRemoteDataSource.getRandomMovie(page)

    fun getPreference(withCast:String,withGenre:String)=moviesRemoteDataSource.getPreference(withCast, withGenre)
    //actorIds: Array<Int>, genreIds: Array<Int>  actorIds, genreId
    fun getSearchedMovies(query: String)=moviesRemoteDataSource.getSearchedMovies(query)

    fun getAllLocalMovies()=moviesLocalDataSource.getAll()
   // fun getRandomMovie(page: int)=moviesLocalDataSource.get()
    fun getAllRemoteMovies(movieRandomPage: Int) =moviesLocalDataSource.getAll()
    fun getFavorite()=moviesLocalDataSource.getFavorite()
    fun getWatched()=moviesLocalDataSource.getWatched()
    fun saveLocal(movie: Movie) = moviesLocalDataSource.save(movie)
    fun saveAllLocal(movie: List<Movie>) = moviesLocalDataSource.saveAll(movie)
    fun deleteLocal(movie: Movie) = moviesLocalDataSource.delete(movie)
    fun deleteAllLocal() = moviesLocalDataSource.deleteAll()
    fun deleteAllLocal(movie: List<Movie>) = moviesLocalDataSource.deleteAll(movie)
    fun replaceAllLocal(movie: List<Movie>) = moviesLocalDataSource.replaceAll(movie)
    fun getCount()=moviesLocalDataSource.getCount()
}