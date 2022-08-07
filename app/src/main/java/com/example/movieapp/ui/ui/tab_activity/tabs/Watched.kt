package com.example.movieapp.ui.ui.tab_activity.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Watched: Fragment(R.layout.fragment_watched_list) {

    private val movieRepository: MovieRepository = MovieRepository.instance
    private var movie:MutableList<Movie> = mutableListOf()

    private fun setupRecyclerView(view: View)
    {
        val liniarLayoutManager= LinearLayoutManager(view.context)
        val recyclerView =view.findViewById<RecyclerView>(R.id.rvWatchedMovieList)

        liniarLayoutManager.orientation= LinearLayoutManager.VERTICAL
        liniarLayoutManager.reverseLayout=false

        recyclerView.layoutManager=liniarLayoutManager
        recyclerView.adapter=WatchedMoviesAdapter(movie)
    }
    private fun initializeListOfMovies(view: View){
        GlobalScope.launch(Dispatchers.IO) {
            movie = movieRepository.getWatched().toMutableList()
            withContext(Dispatchers.Main)
            {
                setupRecyclerView(view)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListOfMovies(view)
    }
}