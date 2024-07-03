package com.netgear.homework.movie.repository

import com.netgear.homework.movie.repository.data.Movie

class MovieDetailRepository: MovieApiDataSource<Movie>() {

    suspend fun getMovieDetails(movieId: Int): Result<Movie?> = requestMovieDataSource {
            retrofit.create(ApiService::class.java).getMovieDetails(
                TOKEN, movieId
            )
    }
}