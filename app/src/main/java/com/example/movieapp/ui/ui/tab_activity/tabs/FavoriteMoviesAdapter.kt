package com.example.movieapp.ui.ui.tab_activity.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieRepository
import com.example.movieapp.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteMoviesAdapter (
    private val moviesList: MutableList<Movie>
) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val itemIvMovie = view.findViewById<ImageView>(R.id.ImgMovie)!!
        val itemIvTitle = view.findViewById<TextView>(R.id.txtMovieTitle)!!

        val itemIvOverview = view.findViewById<TextView>(R.id.txtMovieDescription)!!

    }

    private val moviesRep: MovieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(holder.itemView.context).load( Constant.LinkPoza + moviesList[position].image )
            .into( holder.itemIvMovie)

        holder.itemIvTitle.text = movie.title
        holder.itemIvOverview.text = movie.description

        holder.favorite = moviesList[position].isFavorite
        holder.watched = moviesList[position].isWatched

    }

    private fun updateItem(movie: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList(moviesRep.getAllLocalMovies())
            val idx = saved.indexOf(movie)
            if (idx != -1) saved[idx].isFavorite = !saved[idx].isFavorite
            if (!saved[idx].isFavorite && !saved[idx].isWatched) {
                moviesRep.deleteLocal(saved[idx])
                saved.removeAt(idx)
            }
            moviesRep.replaceAllLocal(saved.toList())
        }
    }

    override fun getItemCount() = moviesList.size
}