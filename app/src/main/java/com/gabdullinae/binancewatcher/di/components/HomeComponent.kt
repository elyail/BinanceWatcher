package com.gabdullinae.binancewatcher.di.components

import com.gabdullinae.binancewatcher.home.HomeViewModel
import com.gabdullinae.binancewatcher.di.modules.HomeModule
import dagger.Subcomponent

@Subcomponent(modules = [(HomeModule::class)])
interface HomeComponent {

    fun homeViewModel(): HomeViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): HomeComponent
    }
}