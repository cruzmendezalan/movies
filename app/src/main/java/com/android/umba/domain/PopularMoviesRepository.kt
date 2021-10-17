package com.android.umba.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.umba.data.api.MovieDbServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val apiServices: MovieDbServices,
    private val apiConfiguration: ApiConfigurationProvider,
) {
    fun getPopularMoviesResultStream(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = true, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    apiServices,
                    apiConfiguration
                )
            }
        ).flow
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
