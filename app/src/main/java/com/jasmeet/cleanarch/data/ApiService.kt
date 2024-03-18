package com.jasmeet.cleanarch.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com"
    }

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Dogs>
}