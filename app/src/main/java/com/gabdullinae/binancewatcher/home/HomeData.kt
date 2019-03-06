package com.gabdullinae.binancewatcher.home

import com.gabdullinae.domain.entity.CurrencyInfo

sealed class HomeData
class CurrenciesInfoState(var items: List<CurrencyInfo>) : HomeData()
