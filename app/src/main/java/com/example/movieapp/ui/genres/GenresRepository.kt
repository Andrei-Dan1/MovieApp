package com.example.movieapp.ui.genres;

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient;

public class GenresRepository {
    companion object {
        val instance = GenresRepository()

    }
    private val genresRemoteDataSource=GenresRemoteDataSource(APIClient.instance.retrofit)
    private val genresLocalDataSource=GenresLocalDataSource(Database.instance)

    fun getAllRemoteGenres() = genresRemoteDataSource.getGenres()
    fun getAllLocalIds() = genresLocalDataSource.getAllIds()
    fun getAllLocalGenres()=genresLocalDataSource.getAll()
    fun saveLocal(genre: Genre) = genresLocalDataSource.save(genre)
    fun saveAllLocal(genres: List<Genre>) = genresLocalDataSource.saveAll(genres)
    fun deleteLocal(genre: Genre) = genresLocalDataSource.delete(genre)
    fun deleteAllLocal() = genresLocalDataSource.deleteAll()
    fun deleteAllLocal(genres: List<Genre>) = genresLocalDataSource.deleteAll(genres)
    fun replaceAllLocal(genres: List<Genre>) = genresLocalDataSource.replaceAll(genres)
    fun getCount()=genresLocalDataSource.getCount()

}

