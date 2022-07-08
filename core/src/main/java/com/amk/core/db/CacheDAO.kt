package com.amk.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amk.core.entity.Company
import com.amk.core.utils.NOTE_TABLE
import com.amk.core.utils.SHORT_NAME

@Dao
interface CacheDAO {
    @Insert
    suspend fun addCacheItem(cacheCompany: Company)

    @Update
    suspend fun updateCacheItem(cacheCompany: Company)

    @Query("Select * from $NOTE_TABLE")
    fun selectAllItems(): LiveData<List<Company>>

    @Query("DELETE FROM $NOTE_TABLE")
    suspend fun clearCache()

    @Query("DELETE FROM $NOTE_TABLE WHERE $SHORT_NAME = :shortName")
    suspend fun deleteCacheItemByShortName(shortName: String)
}