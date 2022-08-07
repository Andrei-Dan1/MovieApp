package com.example.movieapp.ui.actores

class ActorsMapper {
    fun map(actorsResponse: ActorsResponse) : Actor {
        return Actor(
            id= actorsResponse.id,
            name=actorsResponse.name,
            isSelected=false
        )
    }
}