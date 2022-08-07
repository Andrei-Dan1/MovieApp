package com.example.movieapp.ui.movie

import com.example.movieapp.database.Database

class MovieLocalDataSource(database: Database) {
        private val movieDao: MovieDAO =database.movieAppDatabase.movieDao()

  //     fun getRandom()=movieDao.getId()
        fun getFavorite()=movieDao.getFavorite()
        fun getWatched()=movieDao.getWatched()
        fun getAll()=movieDao.getAll()
        fun save(movie: Movie) = movieDao.save(movie)
        fun saveAll(movies: List<Movie>) = movieDao.saveAll(movies)
        fun delete(movie: Movie) = movieDao.delete(movie)
        fun deleteAll() = movieDao.deleteAll()
        fun deleteAll(movie: List<Movie>) = movieDao.deleteAll(movie)
        fun replaceAll(movie: List<Movie>) = movieDao.replaceAll(movie)
        fun getCount() = movieDao.getCount()
    }