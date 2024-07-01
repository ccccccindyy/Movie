package com.netgear.homework.movie.repository.data

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    val id:Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val poster: String,
)
