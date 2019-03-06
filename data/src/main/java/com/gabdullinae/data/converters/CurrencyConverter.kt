package com.gabdullinae.data.converters

import com.gabdullinae.data.models.CryptoPairsResponse
import com.gabdullinae.domain.entity.CurrencyInfo

object CurrencyConverter {

    fun fromNetwork(body: CryptoPairsResponse?): List<CurrencyInfo>? {
        return body?.pairs?.map {
            CurrencyInfo(
                    it.firstCurrency as String,
                    it.secondCurrency as String,
                    it.status as String
            )
        }

    }
}