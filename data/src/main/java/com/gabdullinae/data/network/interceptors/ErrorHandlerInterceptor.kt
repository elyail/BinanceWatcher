package com.gabdullinae.data.network.interceptors

import com.gabdullinae.data.R
import com.gabdullinae.data.models.ApiErrorBody
import com.gabdullinae.data.network.exceptions.CommonException
import com.gabdullinae.data.network.exceptions.NetworkException
import com.gabdullinae.data.utils.Resources
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorHandlerInterceptor(val gson: Gson, val resources: Resources) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = try {
            chain.proceed(chain.request())
        } catch (th: Throwable) {
            when (th) {
                is IOException -> throw NetworkException()
                else -> throw th
            }
        }
        if (!response.isSuccessful) {
            val error = gson.fromJson<ApiErrorBody>(response.body()?.string(), ApiErrorBody::class.java)
            throw CommonException(error.errorMessage ?: resources.getString(R.string.common_error))

        }
        return response
    }
}