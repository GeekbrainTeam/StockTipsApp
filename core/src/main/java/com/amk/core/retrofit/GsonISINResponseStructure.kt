package com.amk.core.retrofit

import com.google.gson.annotations.SerializedName

data class GsonISINResponseStructure(val securities: Securities)

data class Securities(
    val metadata: Metadata,
    val columns: List<String>,
    val data: List<List<String>>
)

data class Metadata(
    val id: JsonTypeWrapper,
    val secId: JsonTypeBytesMaxSizeWrapper,
    val shortName: JsonTypeBytesMaxSizeWrapper,
    val regNumber: JsonTypeBytesMaxSizeWrapper,
    val name: JsonTypeBytesMaxSizeWrapper,
    val isin: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("is_traded")
    val isTraded: JsonTypeWrapper,

    @field:SerializedName("emitent_id")
    val emitentID: JsonTypeWrapper,

    @field:SerializedName("emitent_title")
    val emitentTitle: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("emitent_inn")
    val emitentInn: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("emitent_okpo")
    val emitentOkpo: JsonTypeBytesMaxSizeWrapper,

    val gosreg: JsonTypeBytesMaxSizeWrapper,
    val type: JsonTypeBytesMaxSizeWrapper,
    val group: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("primary_boardid")
    val primaryBoardid: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("marketprice_boardid")
    val marketpriceBoardid: JsonTypeBytesMaxSizeWrapper
)