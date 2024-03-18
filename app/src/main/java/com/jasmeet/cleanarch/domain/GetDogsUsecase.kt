package com.jasmeet.cleanarch.domain

import com.jasmeet.cleanarch.data.DogsRepository

class GetDogsUseCase(private val repository: DogsRepository) {
    suspend operator fun invoke(page: Int, limit: Int): List<Dog> {
        return repository.getDogs(page, limit).map { it.toDog() }
    }
}
