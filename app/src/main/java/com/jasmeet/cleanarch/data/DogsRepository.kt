package com.jasmeet.cleanarch.data

import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDogs(
        page: Int,
        limit: Int
    ): List<Dogs> = apiService.getAllDogs(
        page, limit
    )

}