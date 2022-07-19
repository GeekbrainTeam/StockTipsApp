package com.amk.company.getinfo

import com.amk.company.getinfo.retrofit.BankiRuApiService
import com.amk.core.repository.Repository
import org.apache.commons.text.StringEscapeUtils
import com.amk.company.getinfo.retrofit.AlphavantageCoApiService

class InfoRepository(
    private val moexApiService: Repository,
    private val bankiRuApiService: BankiRuApiService,
    private val alphavantageCoApiService: AlphavantageCoApiService
) {

    suspend fun getCompanyInfo(secId: String): CompanyInfo {
        val isin = moexApiService.getISIN(secId)
        if (isin != "?") {
            if (isin.startsWith("RU")) {
                return try {
                    val site = bankiRuApiService.getSite(isin)
                    val bodyText = site.charStream().readText()
                    bodyText.dropLast(bodyText.length / 2)
                    val unescapedBodyText = StringEscapeUtils.unescapeJava(bodyText)
                    CompanyInfo(parseInfo(unescapedBodyText), parseIcon(unescapedBodyText))
                } catch (error: Exception) {
                    CompanyInfo("error", "$error")
                }
            } else {
                val info = alphavantageCoApiService.getCompanyInfo(secId)
                if (info.industry.isNullOrBlank() && info.description.isNullOrBlank()) {
                    return CompanyInfo("error", "no english information")
                } else {
                    var result = "${info.industry}"
                    if (info.description != "None") {
                        result += ", ${info.description}"
                        return CompanyInfo("${info.industry}, ${info.description}", "no image")
                    }
                    return CompanyInfo(result, "no image")
                }
            }
        } else {
            return CompanyInfo("error", "no ISIN")
        }
    }


    private fun parseIcon(unescapedBodyText: String): String {
        val iconText = unescapedBodyText.substringAfter("logo&quot;:&quot;")
        return iconText.substringBefore("&quot;,&quot")
    }

    private fun parseInfo(unescapedBodyText: String): String {
        var headerInfoText: String
        var infoText: String
        var info: String

        headerInfoText = unescapedBodyText.substringAfter("span style")
        if (headerInfoText.length != unescapedBodyText.length) {
            infoText = headerInfoText.substringAfter("&quot;&gt;")
            info = infoText.substringBefore("&lt;/span")
            return clearText(info)
        }

        infoText = unescapedBodyText.substringAfter("reference&quot;:&quot;&lt;p&gt;")
        if (infoText.length != unescapedBodyText.length) {
            info = infoText.substringBefore("&lt;/p&gt;&quot")
            return clearText(info)
        }

        infoText = unescapedBodyText.substringAfter("pre-wrap;&quot;&gt;")
        if (infoText.length != unescapedBodyText.length) {
            info = infoText.substringBefore("&lt;/p&gt;")
            return clearText(info)
        }
        return "error parse"
    }

    private fun clearText(sourceInfo: String): String {
        var info = sourceInfo.replace("&amp;laquo;", "\"", false)
        info = info.replace("&amp;raquo;", "\"", false)
        info = info.replace("&amp;mdash;", "-", false)
        info = info.replace("&quot;", "-", false)
        return info
    }
}