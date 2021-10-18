package com.android.movies.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.movies.data.api.MoviesListResponse
import com.android.movies.entities.MovieEntity
import javax.inject.Inject

private const val PAGE_INDEX = 1

class MoviesListPagingSource @Inject constructor(
    private val configuration: ApiConfigurationProvider,
    private val moviesListFetcher: MoviesListFetcher
) : PagingSource<Int, MovieEntity>() {

    private lateinit var results: MoviesListResponse

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_INDEX) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: PAGE_INDEX
        return try {
            results = moviesListFetcher.getMovies(page)
            val movies = results.results.map {
                val result = it.toMovieEntity(
                    configuration.getConfiguration(),
                    moviesListFetcher.getListType()
                )
                result
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == PAGE_INDEX) null else page - 1,
                nextKey = if (page == results.total_pages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}


