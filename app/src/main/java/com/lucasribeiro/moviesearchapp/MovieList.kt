package com.lucasribeiro.moviesearchapp

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val poster: String,
)

data class MovieResponses(
    @SerializedName("Search") val search: List<MovieResponse>,
    @SerializedName("totalResults") val nResponses: Int,
    @SerializedName("Response") val response: Boolean
)