package com.amk.core.entity.company

import com.amk.core.entity.moex_model.json.History
import com.amk.core.entity.moex_model.json.metadata.HistoryCursor
import com.google.gson.annotations.SerializedName

data class MoexCompanyRaw(
    val history: History,
    @SerializedName("history.cursor")
    val cursor: HistoryCursor
)