package com.netgear.homework.movie.repository

import com.netgear.homework.movie.repository.data.MovieSearchResponse


class MovieSearchRepository : MovieApiDataSource<MovieSearchResponse>() {

    suspend fun searchMovie(text: String): Result<MovieSearchResponse?> = requestMovieDataSource {
        retrofit.create(ApiService::class.java).searchMovie(
            TOKEN, text
        )
    }

}
