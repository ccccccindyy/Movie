package com.netgear.homework.movie.service

import com.netgear.homework.movie.repository.data.MovieSearchResult
import com.netgear.homework.movie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieSearchService {
    private val movieSearchRepository = MovieRepository()
    suspend fun searchForMovie(searchTerm: String): List<MovieSearchResult>? {
       return withContext(Dispatchers.IO) {
            movieSearchRepository.requestMovieDataSource(searchTerm)?.results
        }
    }
}
