package com.amk.core.api

import com.amk.core.entity.company.MoexCompanyRaw
import com.amk.core.entity.moex_model.json.History

const val START_PAGE = 0
const val NEXT_PAGE = 100

class MoexServiceNetworkImpl constructor(private val companiesService: MoexApi) :
    MoexServiceNetwork {

    override suspend fun getMoexCandleServiceByCompany(
        secId: String,
        dataFrom: String,
        dataTill: String
    ): MoexCompanyRaw =
        companiesService.getMoexCandleByCompany(secId, dataFrom, dataTill)

    //TODO удалить если не понадобится при пагинации
    private fun mergePages(
        moexCompanyRaw: MoexCompanyRaw,
        moexCompanyRaw2: MoexCompanyRaw?,
    ): MoexCompanyRaw {

        val data = mutableListOf<List<Any>>()

        data.addAll(moexCompanyRaw.history.data)
        moexCompanyRaw2?.let { data.addAll(moexCompanyRaw2.history.data) }
        data.distinct()
        val resultData: List<List<Any>> = data
        val history = History(
            moexCompanyRaw.history.columns,
            data = resultData,
            moexCompanyRaw.history.metadata
        )
        return MoexCompanyRaw(history, moexCompanyRaw.cursor)
    }

    override suspend fun getAllCompany(pageNumber: Int, date: String): MoexCompanyRaw =
        companiesService.getCompaniesByPage(pageNumber, date)
    //TODO переделать с пагинацией на корутинах
    //Загрузка и объединение всех страниц

//            .flatMap { companyListRaw ->
//                val index = companyListRaw.cursor.data[0][0]
//                val total = companyListRaw.cursor.data[0][1]
//                val pageSize = companyListRaw.cursor.data[0][2]
//
//                if (index < total) {
//                    Single.just(companyListRaw)
//                        .zipWith(getAllCompany(pageNumber + pageSize, date)) { current, next ->
//                            mergePages(current, next)
//                        }
//                } else {
//                    Single.just(companyListRaw)
//                }
//
//            }

}