package com.android.movies.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.movies.R
import com.android.movies.databinding.FragmentMoviesListBinding
import com.android.movies.databinding.NetworkStateItemBinding
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var binding: FragmentMoviesListBinding

    /**
     * Describes the category id which is used in the API.
     */
    private lateinit var category: String
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
    ): View = with(FragmentMoviesListBinding.inflate(inflater, container, false)) {
        binding = this
        category = arguments?.getString(getString(R.string.moviesListArgumentKey)).orEmpty()
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
                .fetchMoviesList(category)
                .collectLatest {
                    binding.container.visibility = View.VISIBLE
                    adapter.submitData(it)
                }
        }
    }


    private fun setUpViews() {
        binding.container.adapter = adapter
        binding.container.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter(adapter),
            footer = PostsLoadStateAdapter(adapter)
        )
        lifecycleScope.launchWhenCreated {
//            adapter.loadStateFlow.
//            collect { loadStates ->
//                binding.swipeRefresh.isRefreshing =
//                    loadStates.mediator?.refresh is LoadState.Loading
//            }
        }
    }
}

class PostsLoadStateAdapter(
    private val adapter: MoviesListAdapter
) : LoadStateAdapter<NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }
}

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {
    private val binding = NetworkStateItemBinding.bind(itemView)
    private val progressBar = binding.progressBar
    private val errorMsg = binding.errorMsg
    private val retry = binding.retryButton
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
        errorMsg.text = (loadState as? LoadState.Error)?.error?.message
    }
}


