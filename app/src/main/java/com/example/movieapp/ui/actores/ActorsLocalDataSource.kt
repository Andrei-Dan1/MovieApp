package com.example.movieapp.ui.actores

import com.example.movieapp.database.Database



class ActorsLocalDataSource(database: Database) {

    private val actorsDao: ActorsDAO =database.movieAppDatabase.actorsDao()
    fun getAll()=actorsDao.getAll()
    fun getAllIds()=actorsDao.getAllIds()
    fun save(actor: Actor) = actorsDao.save(actor)
    fun saveAll(actor: List<Actor>) = actorsDao.saveAll(actor)
    fun delete(actor: Actor) = actorsDao.delete(actor)
    fun deleteAll() = actorsDao.deleteAll()
    fun deleteAll(actors: List<Actor>) = actorsDao.deleteAll(actors)
    fun replaceAll(actors: List<Actor>) = actorsDao.replaceAll(actors)
        fun getCount() = actorsDao.getCount()
}