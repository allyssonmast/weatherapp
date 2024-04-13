package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.QueryEntity

interface LocalRepository {
    suspend fun saveQuery(query: QueryEntity)
    fun getAllQueries(): List<QueryEntity>
}