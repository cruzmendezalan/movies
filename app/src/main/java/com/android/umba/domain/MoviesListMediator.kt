package com.android.umba.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.android.umba.data.database.MoviesDao
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class MoviesListMediator @Inject constructor(
    private val moviesListDao: MoviesListDao,
    private val moviesDao: MoviesDao,
    private val db: MoviesDatabase,
    private val apiConfiguration: ApiConfigurationProvider
) : RemoteMediator<Int, MovieEntity>() {

    var moviesStreamCallback: MoviesStream? = null

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        moviesListDao.remoteKeyByPost(
                            moviesStreamCallback?.getListType().orEmpty()
                        )
                    }

                    if (remoteKey.nextPageKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPageKey
                }
            }

            val data = moviesStreamCallback?.getMovies(page = loadKey ?: 1)
            val items = data?.results?.map {
                it.toMovieEntity(
                    apiConfiguration.getConfiguration(),
                    moviesStreamCallback?.getListType().orEmpty()
                )
            } ?: listOf()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDao.deleteByListName(moviesStreamCallback?.getListType().orEmpty())
                    moviesListDao.deleteByListName(
                        moviesStreamCallback?.getListType().orEmpty()
                    )
                }

                moviesListDao.insert(
                    MoviesListName(
                        moviesStreamCallback?.getListType().orEmpty(),
                        if (data?.total_pages ?: 0 <= data?.page ?: 0) {
                            null
                        } else {
                            (data?.page ?: 0) + 1
                        }
                    )
                )
                moviesDao.insertAll(items)
            }

            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

}
