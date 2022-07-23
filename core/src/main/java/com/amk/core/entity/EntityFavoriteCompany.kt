package com.amk.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amk.core.utils.COMPANY_IS_FAVORITE
import com.amk.core.utils.SEC_ID
import java.io.Serializable

@Entity(tableName = COMPANY_IS_FAVORITE)
data class EntityFavoriteCompany(
    @PrimaryKey
    @ColumnInfo(name = SEC_ID)
    val secId: String
) : Serializable
