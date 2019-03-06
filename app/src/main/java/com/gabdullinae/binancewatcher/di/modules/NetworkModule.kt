package com.gabdullinae.binancewatcher.di.modules

import android.util.Log
import com.gabdullinae.data.network.api.BinanceRestApi
import com.gabdullinae.data.network.interceptors.ErrorHandlerInterceptor
import com.gabdullinae.data.utils.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.binance.com/"
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(
            gson: Gson,
            resources: Resources
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { text -> Log.d("REST", text) }
        )
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ErrorHandlerInterceptor(gson, resources))
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    internal fun provideRest(retrofit: Retrofit): BinanceRestApi = retrofit.create(BinanceRestApi::class.java)
}