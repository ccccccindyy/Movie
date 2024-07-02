package com.netgear.homework.movie.service

import com.netgear.homework.movie.repository.data.MovieSearchResult
import com.netgear.homework.movie.repository.MovieSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieSearchService {
    private val movieSearchRepository = MovieSearchRepository()
    suspend fun searchForMovie(searchTerm: String): List<MovieSearchResult>? {
       return withContext(Dispatchers.IO) {
            movieSearchRepository.searchMovie(searchTerm)?.results
        }
    }
}
