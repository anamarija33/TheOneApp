package org.unizd.rma.brkic.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val movie : Movie,
    val runtimeInMinutes: String,
    val budgetInMillions:String ,
    val boxOfficeRevenueInMillions: String,
    val academyAwardNominations: String,
    val academyAwardWins: String,
    val rottenTomatoesScore: String
)

data class Movie(
    @SerializedName("_id") val id: String,
    val name : String
)