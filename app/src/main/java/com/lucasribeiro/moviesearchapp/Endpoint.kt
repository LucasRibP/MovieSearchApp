package com.lucasribeiro.moviesearchapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("/")
    fun getSearch(@Query("apikey")apiKey: String, @Query("s")searchTerm: String): Call<MovieResponses>
}