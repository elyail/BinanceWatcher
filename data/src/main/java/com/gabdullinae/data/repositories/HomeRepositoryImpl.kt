package com.gabdullinae.data.repositories

import com.gabdullinae.data.converters.CurrencyConverter
import com.gabdullinae.data.models.CryptoPairsResponse
import com.gabdullinae.data.network.api.BinanceRestApi
import com.gabdullinae.domain.entity.CurrencyInfo
import com.gabdullinae.domain.repositories.HomeRepository
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val restApi: BinanceRestApi) : HomeRepository {
    override fun getAllCurrenciesInfo(): Single<List<CurrencyInfo>> {
        return restApi.getCryptoPairs()
                .map { it.body() }
                .map { processResponse(it) }
    }

    private fun processResponse(response: CryptoPairsResponse?) = CurrencyConverter.fromNetwork(response)

}