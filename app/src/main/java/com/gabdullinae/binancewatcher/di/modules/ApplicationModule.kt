package com.gabdullinae.binancewatcher.di.modules

import android.content.Context
import com.gabdullinae.data.utils.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideGson(): Gson = Gson()

    @Provides
    internal fun resources(context: Context) = Resources(context)
}