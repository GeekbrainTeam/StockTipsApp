package com.amk.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amk.core.entity.CacheModel
import com.amk.core.utils.DB_NAME

@Database(
    entities = [CacheModel::class],
    version = 1
)
abstract class CacheDb : RoomDatabase() {
    abstract fun cacheDAO(): CacheDAO

    companion object {
        @Volatile
        private var INSTANCE: CacheDb? = null

        fun getDatabase(context: Context): CacheDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CacheDb::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}