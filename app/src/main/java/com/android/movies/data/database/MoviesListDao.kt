package com.android.movies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.movies.entities.MoviesListName

@Dao
interface MoviesListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: MoviesListName)

    @Query("SELECT * FROM movies_list_name WHERE listName = :listName")
    suspend fun remoteKeyByPost(listName: String): MoviesListName

    @Query("DELETE FROM movies_list_name WHERE listName = :listName")
    suspend fun deleteByListName(listName: String)
}
