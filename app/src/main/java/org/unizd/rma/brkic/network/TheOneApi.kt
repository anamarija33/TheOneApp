package org.unizd.rma.brkic.network

import androidx.core.os.BuildCompat
import com.google.gson.internal.GsonBuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.unizd.rma.brkic.BuildConfig
import org.unizd.rma.brkic.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.logging.LoggingPermission


interface TheOneApi {

// https://medium.com/@1550707241489/how-to-add-headers-to-retrofit-android-kotlin-450da34d3c3a

    @GET("movie")
    suspend fun getMovies(): Response<List<MovieResponse>>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: String): Response<MovieResponse>

    companion object {
        const val BASE_URL = "https://the-one-api.dev/v2/"
        const val BEARER = BuildConfig.API_BEARER
    }

}