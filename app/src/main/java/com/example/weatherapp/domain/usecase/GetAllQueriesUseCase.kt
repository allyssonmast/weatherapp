package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.QueryEntity
import com.example.weatherapp.domain.repository.LocalRepository
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllQueriesUseCase @Inject constructor(private val localQueryRepository: LocalRepository) {

    fun invoke(): Flow<Resource<List<QueryEntity>>> {
        return flow {
            try {
                val queries = localQueryRepository.getAllQueries()
                emit(Resource.Success(queries))
            } catch (e: Exception) {
                emit(Resource.Error("Failed to get queries: ${e.message}"))
            }
        }
    }
}