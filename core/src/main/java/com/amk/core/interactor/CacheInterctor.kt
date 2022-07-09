package com.amk.core.interactor

import androidx.lifecycle.LiveData
import com.amk.core.entity.CacheModel
import com.amk.core.entity.Company
import com.amk.core.entity.CompanyIsShow

interface CacheInterctor  {
    val dataFromCache: LiveData<List<CacheModel>>
    val dataFromInternetToday: List<Company>
    val dataFromInternetLast: List<Company>
    suspend fun compareCacheVsInternet(): List<CompanyIsShow>
}