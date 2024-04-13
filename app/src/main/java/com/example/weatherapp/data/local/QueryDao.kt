package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.domain.model.QueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: QueryEntity)

    @Query("SELECT * FROM query_table ORDER BY timestamp DESC")
    fun getAllQueries(): Flow<List<QueryEntity>>
}