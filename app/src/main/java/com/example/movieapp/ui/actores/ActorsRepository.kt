package com.example.movieapp.ui.actores

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient


public class ActorsRepository {
        companion object {
            val instance = ActorsRepository()

        }
        private val actorsRemoteDataSource= ActorsRemoteDataSource(APIClient.instance.retrofit)
        private val actorsLocalDataSource= ActorsLocalDataSource(Database.instance)

    fun getAllLocalActors()=actorsLocalDataSource.getAll()
    fun getAllLocalIds() = actorsLocalDataSource.getAllIds()
    fun saveLocal(actors: Actor) =actorsLocalDataSource.save(actors)
    fun saveAllLocal(actors: List<Actor>) = actorsLocalDataSource.saveAll(actors)
    fun deleteLocal(actors: Actor) = actorsLocalDataSource.delete(actors)
    fun deleteAllLocal() = actorsLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Actor>) = actorsLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Actor>) =actorsLocalDataSource.replaceAll(actors)
    fun getCount()=actorsLocalDataSource.getCount()
    fun getAllRemoteActors() = actorsRemoteDataSource.getActors()
    }
