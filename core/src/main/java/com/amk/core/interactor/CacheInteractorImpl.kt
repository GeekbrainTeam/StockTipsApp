package com.amk.core.interactor

import com.amk.core.entity.Company
import com.amk.core.repository.RepositoryCacheDb
import com.amk.core.repository.RepositoryInternet

class CacheInteractorImpl(
    private val repositoryCacheDb: RepositoryCacheDb,
    repository: RepositoryInternet
) : CacheInterctor {
    private val dataGetReadyIsShow = mutableListOf<Company>()
    override val dataFromCache = repositoryCacheDb.selectAllItems()
    override val dataFromInternet = repository.getCompanies()
    override suspend fun compareCacheVsInternet(): List<Company> {
        //Проверка на пустую кэш
        if (dataFromCache.value?.isEmpty() == true) {
            dataFromInternet.value?.forEach {
                repositoryCacheDb.addCacheItem(it)
            }
        }
        // Проверка на наличие новых элементов из Инета
        dataFromInternet.value?.forEach {
            if (dataFromCache.value?.contains(it) == false) {
                repositoryCacheDb.addCacheItem(it)
            }
        }
        // Проверка на непустые данные из инета
        if (dataFromInternet.value?.isEmpty() != true) {
            // Проверка на дату обновления
            dataFromInternet.value?.forEach { itInternet ->
                dataFromCache.value?.forEach { itCache ->
                    // Если дата новая, то на выход данные из кэша
                    if (itCache.tradeDate == itInternet.tradeDate) {
                        dataGetReadyIsShow.add(itCache)
                    } else { //Если старая, то на выход данные из инета и обновление данных в кэше
                        dataGetReadyIsShow.add(itInternet)
                        repositoryCacheDb.updateCacheItem(itInternet)
                    }
                }
            }
        } else {
            //Если данные не пришли
            dataFromCache.value?.forEach {
                dataGetReadyIsShow.add(it)
            }
        }

        return dataGetReadyIsShow
    }
}

