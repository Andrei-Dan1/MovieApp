package com.example.movieapp.ui.movie

import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title :String,
    //@SerializedName("with_genre")
    // var with_qenre : String,
    @SerializedName("overview")
    var description: String?,
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("release_date")
    val date: String?,

    //@SerializedName("with_cast")
    // var with_cast : String,
){

    }