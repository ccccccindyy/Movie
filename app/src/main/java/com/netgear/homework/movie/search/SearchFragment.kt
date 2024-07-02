package com.netgear.homework.movie.search

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.netgear.homework.movie.R
import com.netgear.homework.movie.repository.data.MovieSearchResult

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()

    private val searchView by lazy {
        MovieSearchView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return searchView.initSearchView(inflater, container,
            onQueryTextListener = onQueryTextListener(),
            onMovieClicked = { onMovieClicked(it) })
    }

    private fun onQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                query?.let {
                    viewModel.searchForMovie(
                        query
                    ) {
                        it?.let { searchView.displaySearchResult(it) }
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
    }

    private fun onMovieClicked(movieSearchResult: MovieSearchResult) {
        findNavController().run {
//            currentBackStackEntry?.savedStateHandle?.set("movieId", movieSearchResult.id)
//            navigate(R.id.action_SearchFragment_to_DetailFragment)
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieSearchResult.id)
            this.navigate(action)
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
