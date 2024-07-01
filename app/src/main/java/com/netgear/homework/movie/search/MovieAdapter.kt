package com.netgear.homework.movie.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.netgear.homework.movie.R
import com.netgear.homework.movie.repository.data.MovieSearchResult

class MovieAdapter(private val itemClickListener: (MovieSearchResult) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieSearchResults: List<MovieSearchResult> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieSearchResults[position]
        holder.movieTitle.text = movie.title
        holder.releaseDate.text = movie.releaseDate
        Glide.with(holder.itemView.context)
            .load("https://media.themoviedb.org/t/p/w440_and_h660_face" + movie.poster)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.postView)
        holder.itemView.setOnClickListener {
            itemClickListener(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieSearchResults.size
    }

    fun updateMovies(newMovieSearchResults: List<MovieSearchResult>) {
        movieSearchResults = newMovieSearchResults
        notifyDataSetChanged()
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val movieTitle: TextView = view.findViewById(R.id.item_title)
    val releaseDate: TextView = view.findViewById(R.id.item_release_date)
    val postView: ImageView = view.findViewById(R.id.item_poster)
}
