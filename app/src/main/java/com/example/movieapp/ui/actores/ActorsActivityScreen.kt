package com.example.movieapp.ui.actores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsActivityScreen: AppCompatActivity() {

    private var actors: List<Actor> = emptyList()
    private var actorsRepository = ActorsRepository.instance

    private fun getActors()
    {  GlobalScope.launch (Dispatchers.IO) {
        actors = actorsRepository.getAllRemoteActors()
        withContext(Dispatchers.Main) {
            preselectSavedActors()
        }
    }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_screen)
        setOnClickListeners()
        getActors()
    }

    private fun setOnClickListeners()
    {
        val btnSave=findViewById<FloatingActionButton>(R.id.btnSave)
        btnSave.setOnClickListener(){
            saveActors()
        }
    }

    private fun getSelectedActors(): List<Actor>{
        return actors.filter{ actors -> actors.isSelected }
    }
    private fun saveActors()
    {
        GlobalScope.launch( Dispatchers.IO ) {

            actorsRepository.replaceAllLocal(getSelectedActors())
        }

        finish()

    }
    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvActors)
        rvGenres.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = ActorsAdapter(actors)
    }
    private fun preselectSavedActors(){
        GlobalScope.launch(Dispatchers.IO){
            val savedActors: List<Actor> =actorsRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach{
                        actor -> actor.isSelected = savedActors.contains(actor)
                }
                setupRecyclerView()
            }
        }
    }
}
