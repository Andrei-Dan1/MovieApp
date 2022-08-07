package com.example.movieapp.ui.actores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.genres.GenresAddapter

class ActorsAdapter(private val actorsList: List<Actor>)
    : RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorsName: TextView = view.findViewById(R.id.AcName)
        val heartIcon: ImageView = view.findViewById((R.id.heart))
        val parentView: ConstraintLayout = view.findViewById((R.id.parent1))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actors = actorsList[position]
        holder.actorsName.text = actors.name


        selectActors(holder, actors)
        holder.parentView.setOnClickListener {
            actors.isSelected = !actors.isSelected
            selectActors(holder,actors)
            }
        }
    private fun selectActors(holder: ActorsAdapter.ViewHolder, actor: Actor) {
        holder.parentView.setBackgroundColor(
            when (actor.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.teal_700)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.black)
            }
        )
        holder.heartIcon.visibility = when (actor.isSelected) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }
        override fun getItemCount() = actorsList.size


}


