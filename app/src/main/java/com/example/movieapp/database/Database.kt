package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.ui.actores.Actor
import com.example.movieapp.ui.actores.ActorsDAO
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresDAO
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieDAO

class Database private constructor(){
      companion object{
          val instance=Database()
      }

    @androidx.room.Database(
        entities = [Genre::class, Actor::class, Movie::class],
        version = 1
    )
    abstract class MovieAppDatabase : RoomDatabase(){
        abstract fun actorsDao(): ActorsDAO
        abstract fun genresDao(): GenresDAO
        abstract fun movieDao(): MovieDAO
    }

    lateinit var movieAppDatabase : MovieAppDatabase
    private  set

    fun initialise(context: Context){
        this.movieAppDatabase= Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
                    "movie_app_db"
        ).fallbackToDestructiveMigration().build()
    }
}