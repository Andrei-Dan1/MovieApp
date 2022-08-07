package com.example.movieapp.ui.genres

import androidx.room.*

@Dao
interface GenresDAO {

    @Query("SELECT * from genres")
    fun getAll():List<Genre>

    @Query("SELECT id from genres")
    fun getAllIds():List<Int>

    @Insert
    fun save(genre: Genre)

    @Insert
    fun saveAll(genres : List<Genre>)

    @Delete
    fun delete(genre: Genre)

    @Delete
    fun deleteAll(genre: List<Genre>)

    @Query("delete from genres")
    fun deleteAll()

    @Transaction
    fun replaceAll(genres:List<Genre>){
        deleteAll()
        saveAll(genres)
    }

    @Query("SELECT COUNT(id) from genres")
    fun getCount():Int
}