package com.android.umba.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.umba.data.database.MoviesDao

@Database(entities = [MovieEntity::class, MoviesListName::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getListNameRemoteKeyDao(): MoviesListDao

    abstract fun getMoviesDao(): MoviesDao
}
