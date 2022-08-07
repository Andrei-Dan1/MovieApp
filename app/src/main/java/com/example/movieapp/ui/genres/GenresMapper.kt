package com.example.movieapp.ui.genres;

class GenresMapper {
    fun map(genresResponse: GenresResponse) : Genre{
        return Genre(
            id= genresResponse.id,
            name=genresResponse.name,
            isSelected=false
        )
    }
}
