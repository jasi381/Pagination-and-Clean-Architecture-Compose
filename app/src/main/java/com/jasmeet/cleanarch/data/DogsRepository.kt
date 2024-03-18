package com.jasmeet.cleanarch.data


interface DogsRepository {
    suspend fun getDogs(page: Int, limit: Int): List<Dogs>
}
