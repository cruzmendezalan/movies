package com.android.umba.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListNameRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: ListNameRemoteKey)

    @Query("SELECT * FROM remote_keys WHERE listName = :listName")
    suspend fun remoteKeyByPost(listName: String): ListNameRemoteKey

    @Query("DELETE FROM remote_keys WHERE listName = :listName")
    suspend fun deleteByListName(listName: String)
}
