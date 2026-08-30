package org.unizd.rma.brkic.network


import org.unizd.rma.brkic.BuildConfig


import org.unizd.rma.brkic.model.MoviesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path



interface TheOneApi {

// https://medium.com/@1550707241489/how-to-add-headers-to-retrofit-android-kotlin-450da34d3c3a

    @GET("movie")
    suspend fun getMovies(): Response<MoviesApiResponse>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: String): Response<MoviesApiResponse>

    companion object {
        const val BASE_URL = "https://the-one-api.dev/v2/"
        val BEARER = BuildConfig.API_BEARER
    }

}