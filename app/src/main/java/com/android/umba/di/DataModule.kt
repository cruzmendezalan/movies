package com.android.umba.di

import android.content.Context
import androidx.room.Room
import com.android.umba.domain.ListNameRemoteKeyDao
import com.android.umba.domain.MoviesDatabase
import com.android.umba.domain.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MoviesDatabase::class.java.name
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getListNameRemoteKeyDao(database: MoviesDatabase): ListNameRemoteKeyDao {
        return database.getListNameRemoteKeyDao()
    }

    @Singleton
    @Provides
    fun getMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }
}
