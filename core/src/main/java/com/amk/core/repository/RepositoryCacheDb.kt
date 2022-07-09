package com.amk.core.repository

import androidx.lifecycle.LiveData
import com.amk.core.db.CacheDAO
import com.amk.core.entity.CacheModel
import com.amk.core.entity.Company

class RepositoryCacheDb(private val cacheDAO: CacheDAO) : CacheDAO {

    override suspend fun addCacheItem(cacheCompany: CacheModel) {
        cacheDAO.addCacheItem(cacheCompany)
    }

    override suspend fun updateCacheItem(cacheCompany: CacheModel) {
        cacheDAO.updateCacheItem(cacheCompany)
    }

    override fun selectAllItems(): LiveData<List<CacheModel>> = cacheDAO.selectAllItems()
    override suspend fun selectItem(shortName: String): CacheModel {
        return cacheDAO.selectItem(shortName)
    }

    override suspend fun clearCache() {
        cacheDAO.selectAllItems()
    }

    override suspend fun deleteCacheItemByShortName(shortName: String) {
        cacheDAO.deleteCacheItemByShortName(shortName)
    }

}