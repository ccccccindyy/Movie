package com.netgear.homework.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netgear.homework.movie.repository.data.Movie
import com.netgear.homework.movie.service.MovieDetailService
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val movieDetailService = MovieDetailService()

    fun fetchMovieDetail(movieId: Int, displayMovieDetail: (Movie?) -> Unit) {
        viewModelScope.launch {
            movieDetailService.fetchMovieDetails(movieId).run {
                displayMovieDetail.invoke(this)
            }
        }
    }
}