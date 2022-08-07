package com.example.movieapp.ui.genres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.OnboardingActivity.OnboardingActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresActivityScreen : AppCompatActivity() {

    private var genres: List<Genre> = emptyList()
    private var genresRepository =GenresRepository.instance

    private fun getGenres()
    {  GlobalScope.launch (Dispatchers.IO){
            genres = genresRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main){
                preselectSavedGenres()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        setOnClickListeners()
        getGenres()
    }
    private fun setOnClickListeners()
    {
        val btnSave=findViewById<FloatingActionButton>(R.id.btnSave)
        btnSave.setOnClickListener(){
            saveGenres()
        }
    }

    private fun getSelectedGenres(): List<Genre>{
        return genres.filter{ genre -> genre.isSelected }
    }

    private fun saveGenres()
    {
        GlobalScope.launch( Dispatchers.IO ) {

            genresRepository.replaceAllLocal(getSelectedGenres())
        }

            finish()

        }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenres)
        rvGenres.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAddapter(genres)

    }
    private fun preselectSavedGenres(){
        GlobalScope.launch(Dispatchers.IO){
            val savedGenres: List<Genre> =genresRepository.getAllLocalGenres()
            withContext(Dispatchers.Main) {
                genres.forEach{
                        genre -> genre.isSelected = savedGenres.contains(genre)
                }
                setupRecyclerView()
            }
        }
    }
}