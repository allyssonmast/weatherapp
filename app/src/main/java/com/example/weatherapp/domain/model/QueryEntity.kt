package com.example.weatherapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "query_table")
data class QueryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)