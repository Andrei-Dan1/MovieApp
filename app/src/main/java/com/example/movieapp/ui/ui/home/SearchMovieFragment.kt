package com.example.movieapp.ui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.actores.ActorsRepository
import com.example.movieapp.ui.genres.GenresRepository
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieAdapter
import com.example.movieapp.ui.movie.MovieRepository
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieFragment : Fragment() {

    private var _binding: FragmentSearchMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel:MovieDetailViewModel

    private var movies: List<Movie> = emptyList()

    private val actorsReporsitory=ActorsRepository.instance
    private val movieRepository = MovieRepository.instance
    private val genreRepository = GenresRepository.instance
    private var genreIds =""
    private var actorIds = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(SearchMovieViewModel::class.java)

        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQueryParams()
        setSearchTextListener()
    }

    private fun getQueryParams(){
        preselectSavedGenres()
    }

    private fun preselectSavedGenres(){
        GlobalScope.launch(Dispatchers.IO){
            val savedGenresIds: List<Int> = genreRepository.getAllLocalIds()
            genreIds = savedGenresIds.joinToString(separator = "|"){"$it"}

            val savedActorIds: List<Int> = actorsReporsitory.getAllLocalIds()
            actorIds = savedActorIds.joinToString(separator = "|"){"$it"}

            Log.d("Test", "Rezultat: $genreIds")

            withContext(Dispatchers.Main){
                getMovies()
            }
        }
    }

    private fun getMovies(){
        GlobalScope.launch (Dispatchers.IO){
            movies = movieRepository.getPreference(actorIds,genreIds)
            withContext(Dispatchers.Main){
                moviesLoaded(movies)
            }
        }
        preselectItem()
    }

    private fun setSearchTextListener(){
        val search = binding.SearchMovie
        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if((newText?.length ?:0) >= 1) {
                    getSearchedMovies(newText ?:"")
                } else
                    getMovies()
                return false
            }
        })


    }
    private fun getSearchedMovies(query:String){
        GlobalScope.launch (Dispatchers.IO){
            movies = movieRepository.getSearchedMovies(query)
            withContext(Dispatchers.Main){
              //  moviesLoaded(movies)
                binding.rvMovie.adapter=MovieAdapter(movies, { navigateToMovieDetails() },viewModel)
            }
        }
        preselectItem()
    }

    private fun moviesLoaded(movies:List<Movie>){
        preselectItem()
        setupRecyclerView()

    }

    private fun preselectItem(){
        GlobalScope.launch(Dispatchers.IO)
        {
            val saved=movieRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach {
                    val idx = saved.indexOf(it)
                    it.isFavorite = (idx != -1) && saved[idx].isFavorite
                    it.isWatched = (idx != -1) && saved[idx].isWatched
                }
            }
        }
    }


    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovie
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MovieAdapter(movies, { navigateToMovieDetails() },viewModel)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun navigateToMovieDetails(){
        findNavController().navigate(R.id.nav_movieDetail)
    }

}