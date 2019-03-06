package com.gabdullinae.domain.repositories

import com.gabdullinae.domain.entity.CurrencyInfo
import io.reactivex.Single

interface HomeRepository {
    fun getAllCurrenciesInfo(): Single<List<CurrencyInfo>>
}