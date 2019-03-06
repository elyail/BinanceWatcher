package com.gabdullinae.binancewatcher.di.modules

import com.gabdullinae.binancewatcher.home.HomeViewModel
import com.gabdullinae.data.network.api.BinanceRestApi
import com.gabdullinae.data.repositories.HomeRepositoryImpl
import com.gabdullinae.domain.repositories.HomeRepository
import com.gabdullinae.domain.usecases.HomeUseCase
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomeUseCase(homeRepository: HomeRepository): HomeUseCase = HomeUseCase(homeRepository)

    @Provides
    fun provideAuthRepository(api: BinanceRestApi): HomeRepository = HomeRepositoryImpl(api)

    @Provides
    fun provideViewModel(useCase: HomeUseCase) = HomeViewModel(useCase)
}