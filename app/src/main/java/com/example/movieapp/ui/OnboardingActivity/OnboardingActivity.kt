package com.example.movieapp.ui.OnboardingActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.ui.SearchScreenActivity
import com.example.movieapp.ui.actores.ActorsActivityScreen
import com.example.movieapp.ui.actores.ActorsRepository
import com.example.movieapp.ui.genres.GenresActivityScreen
import com.example.movieapp.ui.genres.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingActivity : AppCompatActivity() {

    val genresRepository = GenresRepository.instance
    val actorsRepository = ActorsRepository.instance

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnboardingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)
        setClickListeners()

    }

    private fun setClickListeners() {
        val genresButton = findViewById<Button>(R.id.buttonGenre)
        val actorsButton = findViewById<Button>(R.id.buttonActor)
        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresActivityScreen::class.java))
        }
        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsActivityScreen::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        verifyIfSelected()
    }

    private fun verifyIfSelected() {
        GlobalScope.launch(Dispatchers.IO){
        val genreCount = genresRepository.getCount()
        val actorCount = actorsRepository.getCount()
        withContext(kotlinx.coroutines.Dispatchers.Main){
        if (genreCount > 0 && actorCount > 0)
            SearchScreenActivity.open(this@OnboardingActivity)
        }
    }
}
}