package com.netgear.homework.movie.repository.data

data class MovieDTO(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val overview: String,
    val poster:String
) {
    fun mapToMovie() = Movie(id, title, year, genre, overview, poster)
}