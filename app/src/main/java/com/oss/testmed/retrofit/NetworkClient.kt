package com.oss.testmed.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private const val BASE_URL = "https://learnfromblogs.com/api/"

    private val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val oktHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(oktHttpClient.build())
        .baseUrl(BASE_URL)
        .build()

    private val networkClient = retrofit.create(APIs::class.java)

    fun getNetworkClient(): APIs {
        return networkClient
    }
}