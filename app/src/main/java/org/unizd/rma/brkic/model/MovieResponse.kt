package org.unizd.rma.brkic.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("_id") val id: String,
    val name : String,
    val runtimeInMinutes: String,
    val budgetInMillions:String ,
    val boxOfficeRevenueInMillions: String,
    val academyAwardNominations: String,
    val academyAwardWins: String,
    val rottenTomatoesScore: String
)

data class MoviesApiResponse(
    val docs: List<MovieResponse>,
    val total: Int,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int
)

data class Movie(
    @SerializedName("_id") val id: String,
    val name : String
)