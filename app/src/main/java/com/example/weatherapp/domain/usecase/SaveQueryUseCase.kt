package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.QueryEntity
import com.example.weatherapp.domain.repository.LocalRepository
import com.example.weatherapp.util.Resource
import javax.inject.Inject

class SaveQueryUseCase @Inject constructor(private val localQueryRepository: LocalRepository) {
    suspend fun invoke(query: String): Resource<Unit> {
        if (query.isBlank()) {
            return Resource.Error("Query cannot be blank")
        }
        return try {
            val queryEntity = QueryEntity(query = query)
            localQueryRepository.saveQuery(queryEntity)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Failed to save query: ${e.message}")
        }
    }
}