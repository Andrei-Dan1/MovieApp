package com.example.movieapp.ui.actores

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var actors: List<ActorsResponse>
)
