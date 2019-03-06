package com.gabdullinae.data.utils

import android.content.Context

class Resources(val context: Context) {

    fun getString(stringId: Int) = context.getString(stringId)
}