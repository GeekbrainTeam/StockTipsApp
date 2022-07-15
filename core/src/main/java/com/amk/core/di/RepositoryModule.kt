package com.amk.core.di

import com.amk.core.db.DataBaseCacheCompany
import com.amk.core.repository.*
import org.koin.dsl.module

val repoModule = module {

    single<RepositoryCompany> {
        RepositoryCompanyImpl(get(), get(), get())
    }

    single<Repository> { NetworkRepository(get()) }

    single { CacheRepository(get()) }

    single { DataBaseCacheCompany.getDatabase(get()) }

    single { get<DataBaseCacheCompany>().cacheDao() }
}
