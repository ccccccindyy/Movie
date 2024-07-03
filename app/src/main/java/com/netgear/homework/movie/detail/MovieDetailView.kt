package com.netgear.homework.movie.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import com.netgear.homework.movie.databinding.FragmentDetailBinding
import com.netgear.homework.movie.repository.data.Movie

class MovieDetailView {
    private lateinit var _binding: FragmentDetailBinding
    private lateinit var detailView: NestedScrollView

    internal fun initDetailView(inflater: LayoutInflater, container: ViewGroup? ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailView = _binding.root
        return detailView
    }

    internal fun displayMovieDetail(movie: Movie?) {
        if(movie != null) {
            detailView.visibility = View.VISIBLE
            _binding.movieTitle.text = movie.title
            _binding.movieDate.text = movie.year.toString()
            _binding.movieGenre.text = movie.genre
            _binding.movieOverview.text = movie.overview
            //todo: load image
        } else {
            //fill empty result message
        }



    }


}