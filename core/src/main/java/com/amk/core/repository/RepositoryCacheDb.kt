package com.amk.core.repository

import androidx.lifecycle.LiveData
import com.amk.core.db.CacheDAO
import com.amk.core.entity.Company

class RepositoryCacheDb(private val cacheDAO: CacheDAO) : CacheDAO {

    override suspend fun addCacheItem(cacheCompany: Company) {
        cacheDAO.addCacheItem(cacheCompany)
    }

    override suspend fun updateCacheItem(cacheCompany: Company) {
        cacheDAO.updateCacheItem(cacheCompany)
    }

    override fun selectAllItems(): LiveData<List<Company>> = cacheDAO.selectAllItems()

    override suspend fun clearCache() {
        cacheDAO.selectAllItems()
    }

    override suspend fun deleteCacheItemByShortName(shortName: String) {
        cacheDAO.deleteCacheItemByShortName(shortName)
    }

}