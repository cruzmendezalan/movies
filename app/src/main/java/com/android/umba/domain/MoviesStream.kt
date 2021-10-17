package com.android.umba.domain

import com.android.umba.data.api.MoviesListResponse

interface MoviesStream {
    fun getMovies(page: Int): MoviesListResponse
    fun getListType(): String
}
