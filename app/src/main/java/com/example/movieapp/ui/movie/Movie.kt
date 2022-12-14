package com.example.movieapp.ui.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    var id: Int,

    @ColumnInfo(name="title")
    var title: String,

    @ColumnInfo(name="description")
    var description: String?,

    var date:String?,

    var image:String?,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "is_watched")
    var isWatched: Boolean,

) {
    override fun equals(other: Any?) = (other is Movie) && id == other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title', description='$description')"
    }
}
