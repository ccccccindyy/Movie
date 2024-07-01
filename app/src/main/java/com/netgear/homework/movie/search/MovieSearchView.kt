package com.netgear.homework.movie.search

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.netgear.homework.movie.databinding.FragmentSearchBinding
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

    internal fun displaySearchResult(movieSearchResults: List<MovieSearchResult>) {
        searchResult.visibility = View.VISIBLE
        (searchResult.adapter as? MovieAdapter)?.updateMovies(movieSearchResults)
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
