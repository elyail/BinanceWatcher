package com.gabdullinae.data.models

import com.google.gson.annotations.SerializedName

data class ApiErrorBody(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("msg")
    var errorMessage: String?
)