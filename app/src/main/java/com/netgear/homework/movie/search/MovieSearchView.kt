package com.netgear.homework.movie.search

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.netgear.homework.movie.databinding.FragmentSearchBinding
import com.netgear.homework.movie.repository.data.MovieSearchResponse
import com.netgear.homework.movie.repository.data.MovieSearchResult

class MovieSearchView {
    private lateinit var _binding: FragmentSearchBinding
    private lateinit var searchView: SearchView
    private lateinit var searchResult: RecyclerView

    internal fun initSearchView(inflater: LayoutInflater, container: ViewGroup?, onQueryTextListener: SearchView.OnQueryTextListener, onMovieClicked: (MovieSearchResult) -> Unit): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchView = _binding.searchView.also {
            it.queryHint = "Search for movies"
            it.setOnQueryTextListener(onQueryTextListener)
        }
        searchResult = _binding.searchResult.apply {
            adapter = MovieAdapter(onMovieClicked)
            this.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, calculateImageSpan())
        }
        return _binding.root
    }

    internal fun displaySearchResult(movieSearchResponse: MovieSearchResponse?) {
        searchResult.visibility = View.VISIBLE
        if(movieSearchResponse != null) {
            (searchResult.adapter as? MovieAdapter)?.updateMovies(movieSearchResponse.results)
            //do something for pagination here
        } else {
            //display empty result message
            _binding.messagebox.text = "No results found"

        }

    }

    internal fun errorPopup(message: String) {
        //TODO: display error message
    }

    private fun calculateImageSpan(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val desiredColumnWidthInDp = 200
        val scale: Float = Resources.getSystem().displayMetrics.density
        val desiredColumnWidthInPixels = (desiredColumnWidthInDp * scale + 0.5f).toInt()
       return (screenWidth / desiredColumnWidthInPixels).coerceAtLeast(1)
    }
}
