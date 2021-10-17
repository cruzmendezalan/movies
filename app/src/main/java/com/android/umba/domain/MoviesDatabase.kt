package com.android.umba.domain

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, ListNameRemoteKey::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getListNameRemoteKeyDao(): ListNameRemoteKeyDao

    abstract fun getMoviesDao(): MoviesDao
}
