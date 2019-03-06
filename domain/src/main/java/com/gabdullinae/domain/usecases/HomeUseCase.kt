package com.gabdullinae.domain.usecases

import com.gabdullinae.domain.entity.CurrencyInfo
import com.gabdullinae.domain.repositories.HomeRepository
import io.reactivex.Single
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: HomeRepository) {
    fun getAllCurrenciesInfo(): Single<List<CurrencyInfo>> {
        return repository.getAllCurrenciesInfo()
    }
}