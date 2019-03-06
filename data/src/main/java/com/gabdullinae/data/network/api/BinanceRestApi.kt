package com.gabdullinae.data.network.api

import com.gabdullinae.data.models.CryptoPairsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface BinanceRestApi {

    @GET("api/v1/exchangeInfo")
    fun getCryptoPairs(): Single<Response<CryptoPairsResponse>>
}