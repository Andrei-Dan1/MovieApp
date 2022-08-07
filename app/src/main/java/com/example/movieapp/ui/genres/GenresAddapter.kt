package com.example.movieapp.ui.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import kotlinx.coroutines.selects.select

class GenresAddapter(private val genreList: List<Genre>)
: RecyclerView.Adapter<GenresAddapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genresName: TextView = view.findViewById(R.id.TvName)
        val parentView: ConstraintLayout = view.findViewById((R.id.parent))
        val methIcon: ImageView = view.findViewById(R.id.methIcon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genreList[position]
        holder.genresName.text = genre.name

        selectGenres(holder, genre)

        holder.parentView.setOnClickListener {
            genre.isSelected = !genre.isSelected
            selectGenres(holder,genre)
        }
        }

    private fun selectGenres(holder: ViewHolder, genre: Genre) {
        holder.parentView.setBackgroundColor(
            when (genre.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.teal_700)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.black)
            }
        )
        holder.methIcon.visibility = when (genre.isSelected) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    override fun getItemCount()= genreList.size

}