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
import com.example.movieapp.databinding.FragmentPopularNowBinding
import com.example.movieapp.ui.movie.Movie
import com.example.movieapp.ui.movie.MovieAdapter
import com.example.movieapp.ui.movie.MovieRepository
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularNowFragment : Fragment(R.layout.fragment_popular_now) {

    private var _binding: FragmentPopularNowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(PopularNowViewModel::class.java)
        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        _binding = FragmentPopularNowBinding.inflate(inflater, container, false)
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
        GlobalScope.launch (Dispatchers.IO){
            movies = movieRepository.getPreference()
            withContext(Dispatchers.Main){
                moviesLoaded(movies)
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
    private fun navigateToMovieDetails(){
        findNavController().navigate(R.id.nav_movieDetail)
    }


}