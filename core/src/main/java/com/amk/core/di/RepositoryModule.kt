package com.amk.core.di

import com.amk.core.repository.NetworkRepository
import com.amk.core.repository.Repository
import org.koin.dsl.module


val repoModule = module {

    single<Repository> {
        return@single NetworkRepository(get())
    }
}