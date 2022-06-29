package com.amk.core.repository

import com.amk.core.retrofit.MoexApiImpl
import com.amk.core.retrofit.MoexApiService
import kotlinx.coroutines.*

class Repository(
    private val activity: View,
    private val apiService: MoexApiService = MoexApiImpl().getMoexService()
) {

    private val scope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun handleError(error: Throwable) {
        activity.showError(error.toString())
    }

    fun getData() {
        scope.launch {
            val response = apiService.getCompanyDataAsync()
            activity.showResult(response.historyCursor.data[0].toString())
        }
    }
}