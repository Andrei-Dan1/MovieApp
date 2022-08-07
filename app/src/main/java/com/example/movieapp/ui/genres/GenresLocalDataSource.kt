package com.example.movieapp.ui.genres

import com.example.movieapp.database.Database

class GenresLocalDataSource(database: Database) {
   private val genreDao: GenresDAO=database.movieAppDatabase.genresDao()
    fun getAll()=genreDao.getAll()
    fun getAllIds()=genreDao.getAllIds()
    fun save(genre: Genre) = genreDao.save(genre)
    fun saveAll(genres: List<Genre>) = genreDao.saveAll(genres)
    fun delete(genre: Genre) = genreDao.delete(genre)
    fun deleteAll() = genreDao.deleteAll()
    fun deleteAll(genres: List<Genre>) = genreDao.deleteAll(genres)
    fun replaceAll(genres: List<Genre>) = genreDao.replaceAll(genres)
    fun getCount() = genreDao.getCount()
}