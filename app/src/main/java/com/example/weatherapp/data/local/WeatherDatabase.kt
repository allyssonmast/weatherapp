package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.domain.model.QueryEntity

@Database(entities = [QueryEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun queryDao(): QueryDao

    companion object {
        const val DATABASE_NAME = "weather_db"
    }
}