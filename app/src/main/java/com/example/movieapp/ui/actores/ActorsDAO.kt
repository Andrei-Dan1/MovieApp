package com.example.movieapp.ui.actores

import androidx.room.*
import com.example.movieapp.ui.actores.Actor

@Dao

interface ActorsDAO {
    @Query("SELECT * from actor")
    fun getAll():List<Actor>

    @Query("SELECT id from actor")
    fun getAllIds():List<Int>

    @Insert
    fun save(actor: Actor)

    @Insert
    fun saveAll(actor : List<Actor>)

    @Delete
    fun delete(actor: Actor)

    @Delete
    fun deleteAll(actor: List<Actor>)

    @Query("delete from actor")
    fun deleteAll()

    @Transaction
    fun replaceAll(actor:List<Actor>){
        deleteAll()
        saveAll(actor)
    }
    @Query("SELECT COUNT(id) from actor")
    fun getCount():Int
}
