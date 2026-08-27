package org.unizd.rma.brkic.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // https://medium.com/@1550707241489/how-to-add-headers-to-retrofit-android-kotlin-450da34d3c3a
    // https://youtu.be/oyKmeW2Kldc
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(LoggingInterceptor())
    }.build()
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TheOneApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val theOneApi: TheOneApi by lazy {
        retrofit.create(TheOneApi::class.java)
    }


}


