package com.netgear.homework.movie.repository.data

data class MovieSearchResponse (
   val page: Int,
   val results: List<MovieSearchResult>,
)
