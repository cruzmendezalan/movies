package com.android.umba.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.umba.data.api.MoviesListResponse
import javax.inject.Inject


private const val POPULAR_MOVIES_STARTING_PAGE_INDEX = 1

class MoviesListPagingSource @Inject constructor(
    private val configuration: ApiConfigurationProvider,
    private val moviesStream: MoviesStream
) : PagingSource<Int, MovieEntity>() {

    private lateinit var results: MoviesListResponse

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: POPULAR_MOVIES_STARTING_PAGE_INDEX
        return try {
            results = moviesStream.getMovies(page)
            val movies = results.results.map {
                val result = it.toMovieEntity(
                    configuration.getConfiguration(),
                    moviesStream.getListType()
                )
                result
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == POPULAR_MOVIES_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == results.total_pages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}


