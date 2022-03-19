package com.oss.testmed.retrofit

import com.oss.testmed.model.Temp
import retrofit2.Response
import retrofit2.http.*

interface APIs {
    @POST("countrylist")
    suspend fun getData(): Response<Temp>
}