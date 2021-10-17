package com.android.umba.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @ColumnInfo(name = "listName") val listName: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieId")
    var movieId: Long = 0,

    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "popularity") val popularity: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)
