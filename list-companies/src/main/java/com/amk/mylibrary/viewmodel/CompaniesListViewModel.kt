package com.amk.mylibrary.viewmodel

import com.amk.core.repository.Repository
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class CompaniesListViewModel {
    private val repository: Repository by inject()

}