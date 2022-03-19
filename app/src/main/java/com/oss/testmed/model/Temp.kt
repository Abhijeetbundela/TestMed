package com.oss.testmed.model

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("errCode")
    val errCode: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("rstr")
        val rstr: String
    )
}