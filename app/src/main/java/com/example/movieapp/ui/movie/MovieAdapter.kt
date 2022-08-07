package com.example.movieapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import com.example.movieapp.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MovieAdapter(
    private val movieList: List<Movie>,
    private val detailsCallback: (() -> Unit),
    private val viewModel:MovieDetailViewModel
    ):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite:Boolean=false
        var watched: Boolean=false
        val title: TextView = view.findViewById(R.id.txtMovieTitle)
        val parentView: ConstraintLayout = view.findViewById((R.id.MovieItem))
        val textDescription : TextView =  view.findViewById((R.id.txtMovieDescription))
        val imageView:ImageView=view.findViewById(R.id.ImgMovie)
        val itemBtnWatched:ImageButton =view.findViewById(R.id.btnWatched)
        val itemBtnFavorite: ImageButton= view.findViewById(R.id.btnFavorite)

    }
    private val movieRepository:MovieRepository  = MovieRepository.instance



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val movie= movieList[position]
        Glide.with(holder.parentView.context).load( Constant.LinkPoza + movieList[position].image ).into( holder.imageView)
        holder.title.text = movie.title
        holder.textDescription.text=movie.description

        holder.favorite=movie.isFavorite
        holder.watched=movie.isWatched

        updateFavoriteButton(holder)
        updateWatchedButton(holder)

        holder.itemBtnFavorite.setOnClickListener{
            holder.favorite=!holder.favorite
            movie.isFavorite=holder.favorite
            updateFavoriteButton(holder)
            updateDatabase(movie)
        }
        holder.itemBtnWatched.setOnClickListener{
            holder.watched=!holder.watched
            movie.isWatched=holder.watched
            updateWatchedButton(holder)
            updateDatabase(movie)

        }

        holder.parentView.setOnClickListener{
           viewModel.currentMovieId.postValue(movie.id)
            detailsCallback.invoke()
        }
    }
    private fun updateFavoriteButton(holder: ViewHolder) {
        holder.itemBtnFavorite.setImageResource(when(holder.favorite) {
            true -> R.drawable.ic_toggled
            else -> R.drawable.ic_favoritfm
        })
    }

    private fun updateWatchedButton(holder: ViewHolder) {
        holder.itemBtnWatched.setImageResource(when(holder.watched) {
            true -> R.drawable.ic_toggled
            else -> R.drawable.ic_watched
        })
    }
    private fun filterWithFlags() = movieList.filter { it.isFavorite || it.isWatched }

    private fun updateDatabase(item: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList<Movie>(movieRepository.getAllLocalMovies())
            val filtered = ArrayList<Movie>(filterWithFlags())
            saved.remove(item)

            movieRepository.replaceAllLocal(saved.union(filtered).toList())
        }
    }


    override fun getItemCount()= movieList.size
}