package com.netgear.homework.movie.repository

import com.netgear.homework.movie.repository.data.Movie
import com.netgear.homework.movie.repository.data.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept: application/json")
    @GET("search/movie")
    fun searchMovie(@Header("Authorization") auth: String,
        @Query("query") query: String
    ): Call<MovieSearchResponse>

    @Headers("Accept: application/json")
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Header("Authorization") auth: String,
        @Path("movie_id") id: Int
    ): Call<Movie>
}
