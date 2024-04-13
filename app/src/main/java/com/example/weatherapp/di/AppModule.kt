package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.util.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton//just one instance
    fun providePaprikaApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        ).build()
    }
}