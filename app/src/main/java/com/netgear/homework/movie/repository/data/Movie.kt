package com.netgear.homework.movie.repository.data

import android.widget.ImageView

data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val overview: String,
    val poster:String
)
