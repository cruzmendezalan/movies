package com.android.umba.presentation.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.umba.domain.GetLatestMovieUseCase
import com.android.umba.domain.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val getLatestMovieUseCase: GetLatestMovieUseCase
) : ViewModel() {

    val state: MutableLiveData<MovieState> = MutableLiveData<MovieState>()

    fun getMovieDetails(movieId: String) {
        if (movieId.isEmpty()) {
            return getLatestMovie()
        }
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(MovieState.MovieDetailsReady(movieDetailsUseCase.invoke(movieId)))
        }
    }

    private fun getLatestMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = getLatestMovieUseCase.invoke()
            state.postValue(MovieState.LatestMovieReady(movie))
        }
    }
}
