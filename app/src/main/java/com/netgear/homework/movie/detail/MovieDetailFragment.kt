package com.netgear.homework.movie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.netgear.homework.movie.databinding.FragmentDetailBinding

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by  viewModels()

    private val detailView by lazy {
        MovieDetailView()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return detailView.initDetailView(inflater, container)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.let { MovieDetailFragmentArgs.fromBundle(it).movieId } ?: -1
        // Use movieId to call viewModel.fetchData(movieId)
        viewModel.fetchMovieDetail(movieId)  {
            it.let {
                if(it.isSuccess) {
                    detailView.displayMovieDetail(it.getOrNull())
                } else {
                    // display error message
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}