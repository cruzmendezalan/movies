package com.android.movies.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.movies.entities.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE listName = :listName ORDER BY indexInResponse ASC")
    fun moviesByListName(listName: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies WHERE listName = :listName")
    suspend fun deleteByListName(listName: String)

    @Query("SELECT MAX(indexInResponse) + 1 FROM movies WHERE listName = :listName")
    suspend fun getNextIndexInListName(listName: String): Int
}
