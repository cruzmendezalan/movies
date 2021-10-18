package com.android.umba.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_list_name")
data class MoviesListName(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val listName: String, // technically mutable but fine for a demo
    val nextPageKey: Int?
)
