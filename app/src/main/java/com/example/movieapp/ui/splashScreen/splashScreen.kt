package com.example.movieapp.ui.splashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.ui.OnboardingActivity.OnboardingActivity
import com.example.movieapp.ui.SearchScreenActivity
import com.example.movieapp.ui.actores.ActorsRepository
import com.example.movieapp.ui.genres.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DELAY = 500L

@SuppressLint("CustomSplashScreen")

class splashScreen : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var genresRepository: GenresRepository = GenresRepository.instance
    private var actorsRepository: ActorsRepository= ActorsRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initHandlerToOpenNextActivity()
    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }

        handler?.postDelayed(runnable!!, DELAY)
    }
    private fun openNextScreen(){
        isSaved()
        finish()
        }

    private fun verifyIfSaved(genreCount :Int, actorCount : Int){
        val isSaved = genreCount>0 && actorCount>0
        if(isSaved)
            SearchScreenActivity.open(this)
        else
            OnboardingActivity.open(this)

    }


    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genresRepository.getCount()
            val actorCount = actorsRepository.getCount()
            withContext(Dispatchers.Main) {
                verifyIfSaved(genreCount, actorCount)

            }
        }
    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}