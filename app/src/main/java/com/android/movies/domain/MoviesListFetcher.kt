package com.android.movies.domain

import com.android.movies.data.api.MoviesListResponse

interface MoviesListFetcher {
    fun getMovies(page: Int): MoviesListResponse
    fun getListType(): String
}
