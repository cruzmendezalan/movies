package com.android.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.movies.entities.MovieEntity
import com.android.movies.entities.MoviesListName

@Database(entities = [MovieEntity::class, MoviesListName::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getListNameRemoteKeyDao(): MoviesListDao

    abstract fun getMoviesDao(): MoviesDao
}
