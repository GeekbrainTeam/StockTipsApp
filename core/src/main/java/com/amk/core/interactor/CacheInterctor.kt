package com.amk.core.interactor

import androidx.lifecycle.LiveData
import com.amk.core.entity.Company

interface CacheInterctor  {
    val dataFromCache: LiveData<List<Company>>
    val dataFromInternet: LiveData<List<Company>>
    suspend fun compareCacheVsInternet(): List<Company>
}