package com.jasmeet.cleanarch.data

class DogsRepositoryImpl(private val apiService: ApiService) : DogsRepository {
    override suspend fun getDogs(page: Int, limit: Int): List<Dogs> {
        return apiService.getAllDogs(page, limit)
    }
}
