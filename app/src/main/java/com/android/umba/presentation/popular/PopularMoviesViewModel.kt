package com.android.umba.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.umba.domain.MovieEntity
import com.android.umba.domain.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
  private val repository: PopularMoviesRepository
) : ViewModel() {

  private var currentSearchResult: Flow<PagingData<MovieEntity>>? = null

  fun fetchPopularMovies(): Flow<PagingData<MovieEntity>> {
    val newEntries: Flow<PagingData<MovieEntity>> =
      repository.getPopularMoviesResultStream().cachedIn(viewModelScope)
    currentSearchResult = newEntries
    return newEntries
  }
}
