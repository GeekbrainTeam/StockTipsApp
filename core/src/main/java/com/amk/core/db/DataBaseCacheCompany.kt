package com.amk.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amk.core.entity.CacheCompanyAfterYesterday
import com.amk.core.entity.CacheCompanyHalfYear
import com.amk.core.entity.CacheCompanyOneDay
import com.amk.core.entity.FavoriteCompany
import com.amk.core.utils.DATABASE_NAME

@Database(
    version = 1,
    entities = [CacheCompanyOneDay::class,
        CacheCompanyAfterYesterday::class,
        CacheCompanyHalfYear::class,
        FavoriteCompany::class]
)
abstract class DataBaseCacheCompany : RoomDatabase() {

    abstract fun cacheDao(): ChacheDao

    companion object

    @Volatile
    private var instanse: DataBaseCacheCompany? = null

    fun getDatabase(context: Context): DataBaseCacheCompany {
        return instanse ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                DataBaseCacheCompany::class.java,
                DATABASE_NAME
            ).build()
            instanse = instance
            instance
        }
    }
}
