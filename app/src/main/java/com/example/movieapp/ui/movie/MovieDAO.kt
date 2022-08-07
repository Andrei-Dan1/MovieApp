package com.example.movieapp.ui.movie

import androidx.room.*
import com.example.movieapp.ui.genres.Genre
@Dao
interface MovieDAO {
    @Query("SELECT * from movies")
    fun getAll():List<Movie>

    @Query("SELECT * from movies")
    fun getAllIds():List<Movie>

    @Insert
    fun save(movie: Movie)

    @Insert
    fun saveAll(movie : List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movie: List<Movie>)

    @Query("delete from movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies:List<Movie>){
        deleteAll()
        saveAll(movies)
    }
    @Query("SELECT * from movies WHERE is_favorite")
    fun getFavorite():List<Movie>

    @Query("SELECT * from movies WHERE is_watched")
    fun getWatched():List<Movie>

    @Query("SELECT COUNT(id) from movies")
    fun getCount():Int
}