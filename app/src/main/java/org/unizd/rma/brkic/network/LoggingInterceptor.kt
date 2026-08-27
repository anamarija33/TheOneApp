package org.unizd.rma.brkic.network

import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization","Bearer "+TheOneApi.BEARER)
            .build()
        return chain.proceed(request)
    }
}