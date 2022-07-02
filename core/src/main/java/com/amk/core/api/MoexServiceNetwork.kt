package com.amk.core.api

import com.amk.core.entity.company.MoexCompanyRaw

interface MoexServiceNetwork {

    suspend fun getAllCompany(pageNumber: Int, date: String): MoexCompanyRaw

    suspend fun getMoexCandleServiceByCompany(
        secId: String,
        dataFrom: String,
        dataTill: String
    ): MoexCompanyRaw

}