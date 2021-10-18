package com.android.movies.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.movies.entities.MovieEntity
import com.android.movies.domain.MoviesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MoviesListRepository
) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<MovieEntity>>? = null

    fun fetchMoviesList(type: String): Flow<PagingData<MovieEntity>> {
        val newEntries: Flow<PagingData<MovieEntity>> =
            repository
                .getMoviesListStream(type)
                .cachedIn(viewModelScope)
        currentSearchResult = newEntries
        return newEntries
    }
}
