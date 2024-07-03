package com.netgear.homework.movie.service

import com.netgear.homework.movie.repository.MovieDetailRepository
import com.netgear.homework.movie.repository.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailService {
    private val movieDetailRepository = MovieDetailRepository()
    suspend fun fetchMovieDetails(movieId: Int): Result<Movie?> {
        return withContext(Dispatchers.IO) {
            movieDetailRepository.getMovieDetails(movieId)
        }
    }
}