package com.gabdullinae.binancewatcher.di.components

import android.content.Context
import com.gabdullinae.binancewatcher.home.HomeActivity
import com.gabdullinae.binancewatcher.di.modules.ApplicationModule
import com.gabdullinae.binancewatcher.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(appContext: Context): Builder

        fun build(): AppComponent
    }

    fun homeBuilder(): HomeComponent.Builder

    fun inject(homeActivity: HomeActivity)
}