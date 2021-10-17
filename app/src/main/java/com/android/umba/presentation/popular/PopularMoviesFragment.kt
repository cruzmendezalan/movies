package com.android.umba.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.umba.databinding.FragmentMoviesBinding
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private var moviesJob: Job? = null

    @Inject
    lateinit var factory: Lazy<MovieHolderFactory>


    private val adapter: MoviesListAdapter by lazy {
        MoviesListAdapter(factory = getMovieHolderFactory())
    }

    private fun getMovieHolderFactory(): MovieHolderFactory {
        return factory.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(FragmentMoviesBinding.inflate(inflater, container, false)) {
        binding = this
        this.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        fetchData()
    }

    private fun fetchData() {
        // Make sure we cancel the previous job before creating a new one
        moviesJob?.cancel()
        moviesJob = lifecycleScope.launch {
            viewModel
                .fetchPopularMovies()
                .collectLatest {
                    binding.container.visibility = View.VISIBLE
                    adapter.submitData(it)
                }
        }
    }

    private fun setUpViews() {
        binding.container.adapter = adapter
    }
}

