package com.netgear.homework.movie.repository

import com.netgear.homework.movie.repository.data.MovieSearchResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MovieSearchRepository : MovieApiDataSource<MovieSearchResponse>() {

    suspend fun searchMovie(text: String): MovieSearchResponse? = requestMovieDataSource {
        retrofit.create(ApiService::class.java).searchMovie(
            TOKEN, text
        )
    }

}
