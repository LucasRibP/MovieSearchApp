package com.lucasribeiro.moviesearchapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface omdbAPI {
    @GET("/")
    suspend fun getSearch(@Query("apikey")apiKey: String, @Query("s")searchTerm: String): Response<ResponseBody>
}