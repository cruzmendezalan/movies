package com.android.umba.domain

import androidx.paging.*
import com.android.umba.data.api.MovieDbServices
import com.android.umba.data.api.PopularMovies
import javax.inject.Inject


private const val POPULAR_MOVIES_STARTING_PAGE_INDEX = 1
const val POPULAR_MOVIES_LIST = "popular_movies"

class PopularMoviesPagingSource @Inject constructor(
    private val services: MovieDbServices,
    private val configuration: ApiConfigurationProvider
) : PagingSource<Int, MovieEntity>() {

    private lateinit var results: PopularMovies

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: POPULAR_MOVIES_STARTING_PAGE_INDEX
        return try {
            results = services.getPopularMovies(page = page)
            val movies = results.results.map {
                val result = it.toMovieEntity(configuration.getConfiguration())
                result
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == POPULAR_MOVIES_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == results.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}


