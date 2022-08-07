package com.example.movieapp.ui.actores

import com.google.gson.annotations.SerializedName

class ActorsResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
){

}