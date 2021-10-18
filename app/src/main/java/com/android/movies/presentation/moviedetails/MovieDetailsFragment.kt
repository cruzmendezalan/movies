package com.android.movies.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.android.movies.R
import com.android.movies.databinding.FragmentMovieDetailsBinding
import com.android.movies.entities.MovieDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(FragmentMovieDetailsBinding.inflate(inflater, container, false)) {
        binding = this
        movieId = arguments?.getString(getString(R.string.toMovieDetailsIdKey)).orEmpty()
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(this, { movieState ->
            when (movieState) {
                is MovieState.Loading -> showLoading()
                is MovieState.MovieDetailsReady -> displayMovieDetails(movieState.movieDetails)
                is MovieState.Errors -> TODO()
                is MovieState.LatestMovieReady -> displayMovieDetails(movieState.movie)
            }
        })

        viewModel.getMovieDetails(movieId)
    }

    private fun showLoading() {

    }

    private fun displayMovieDetails(movieDetails: MovieDetails) {
        binding.movieName.text = movieDetails.original_title
        binding.movieOverview.text = movieDetails.overview

        binding.movieImage.load(movieDetails.backdrop_path) {
            error(R.drawable.ic_image_broke)
        }

        binding.productionCountriesValue.text = movieDetails
            .production_companies
            ?.map { it.name }
            .toString()
    }
}
