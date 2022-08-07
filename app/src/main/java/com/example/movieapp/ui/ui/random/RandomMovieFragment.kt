package com.example.movieapp.ui.ui.popularNow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentRandomBinding
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieAdapter
import com.example.movieapp.ui.movie.MovieRepository
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import com.example.movieapp.ui.ui.random.RandomMovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Math.random
import java.util.*

class RandomMovieFragment : Fragment(R.layout.fragment_random) {

    private var _binding: FragmentRandomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val random = Random()
    private val movieRepository = MovieRepository.instance

    private lateinit var viewModel:MovieDetailViewModel

    private var movies: List<Movie> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(RandomMovieViewModel::class.java)

        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectPopularMovies()

    }

    private fun selectPopularMovies(){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                getMovies()
            }
        }
    }

    private fun getMovies(){
        val movieRandomPage: Int
        movieRandomPage=rand(1 ,500)
        GlobalScope.launch (Dispatchers.IO){
            movies = movieRepository.getAllRemote(movieRandomPage)
            withContext(Dispatchers.Main){
                moviesLoaded(movies)
            }
        }
    }
    private fun generateRandomPage(){
        val randomMovieId= random()
    }

    private fun preselectSavedGenres(){
        GlobalScope.launch(Dispatchers.IO){
            val movieRandomPage: Int
            movieRandomPage=rand(0 ,1000)
            withContext(Dispatchers.Main){
                getMovies()
            }
        }
    }


    private fun moviesLoaded(movies:List<Movie>){
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovie
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MovieAdapter(movies, { navigateToMovieDetails() },viewModel)
    }


    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    private fun navigateToMovieDetails(){
        findNavController().navigate(R.id.nav_movieDetail)
    }

}