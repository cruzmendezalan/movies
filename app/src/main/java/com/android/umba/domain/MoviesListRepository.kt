package com.android.umba.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.umba.R
import com.android.umba.data.api.MovieDbServices
import com.android.umba.data.api.MoviesListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MoviesListRepository @Inject constructor(
    private val apiServices: MovieDbServices,
    private val apiConfiguration: ApiConfigurationProvider,
    private val stringResourceProvider: AndroidStringResourcesProvider

) {
    /**
     * Allow to use the same code in a feature which return a paging list of movies.
     * Using [type] as a selector for the proper data source call.
     */
    fun getMoviesListStream(type: String): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = true, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                MoviesListPagingSource(
                    configuration = apiConfiguration,
                    moviesStream = getMoviesStream(type)
                )
            }
        ).flow
    }

    private fun getMoviesStream(type: String) = object : MoviesStream {
        override fun getMovies(page: Int): MoviesListResponse {
            return runBlocking {
                when (type) {
                    stringResourceProvider.getString(R.string.title_popular_movies) -> apiServices.getPopularMovies(
                        page = page
                    )
                    stringResourceProvider.getString(R.string.title_upcoming_movies) -> apiServices.getUpcomingMovies(
                        page = page
                    )
                    else -> apiServices.getPopularMovies(page = page)
                }
            }
        }

        override fun getListType(): String = type
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}

