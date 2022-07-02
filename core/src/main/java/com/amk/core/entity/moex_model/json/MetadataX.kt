package com.amk.core.entity.moex_model.json

import com.amk.core.entity.moex_model.json.metadata.INDEX
import com.amk.core.entity.moex_model.json.metadata.PAGESIZE
import com.amk.core.entity.moex_model.json.metadata.TOTAL

data class MetadataX(
    val INDEX: INDEX,
    val TOTAL: TOTAL,
    val PAGESIZE: PAGESIZE,
)