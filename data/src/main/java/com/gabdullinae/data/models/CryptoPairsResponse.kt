package com.gabdullinae.data.models

import com.google.gson.annotations.SerializedName

data class CryptoPairsResponse(
    @SerializedName("symbols")
    var pairs: List<PairInfo>
)

data class PairInfo(
    @SerializedName("baseAsset")
    var firstCurrency: String?,
    @SerializedName("quoteAsset")
    var secondCurrency: String?,
    @SerializedName("status")
    var status: String?
)