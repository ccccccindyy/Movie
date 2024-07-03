package com.netgear.homework.movie.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netgear.homework.movie.repository.data.MovieSearchResponse
import com.netgear.homework.movie.repository.data.MovieSearchResult
import com.netgear.homework.movie.service.MovieSearchService
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    fun searchForMovie(searchTerm: String, callBack: (Result<MovieSearchResponse?>) -> Unit) {
        viewModelScope.launch {
            MovieSearchService().searchForMovie(searchTerm).run {
                callBack.invoke(this)
            }
        }
    }
}