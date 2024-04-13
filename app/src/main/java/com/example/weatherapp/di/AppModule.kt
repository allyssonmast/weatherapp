package com.example.weatherapp.di

import DefaultLocationTracker
import android.app.Application
import android.content.Context
import android.location.Geocoder
import androidx.room.Room
import com.example.weatherapp.data.geocoder.GeoCoding
import com.example.weatherapp.data.local.QueryDao
import com.example.weatherapp.data.local.WeatherDatabase
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.model.Location
import com.example.weatherapp.data.repository.WeatherRepositoryImp
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetUserLocationAndCityNameUseCase
import com.example.weatherapp.domain.usecase.SearchWeatherUsecase
import com.example.weatherapp.util.ApiConstants
import com.example.weatherapp.util.connetivity.AppStatus
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi, queryDao: QueryDao): WeatherRepository {
        return WeatherRepositoryImp(queryDao, weatherApi)
    }

    @Provides
    @Singleton
    fun provideQueryDao(weatherDatabase: WeatherDatabase): QueryDao {
        return weatherDatabase.queryDao()
    }

    @Singleton
    @Provides
    fun getConnectivityInterceptor(@ApplicationContext context: Context): AppStatus {
        return AppStatus(context)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
    @Provides
    @Singleton
    fun provideSearchWeatherUsecase(repository: WeatherRepository): SearchWeatherUsecase {
        return SearchWeatherUsecase(repository)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideLocationTracker(locationClient: FusedLocationProviderClient, application: Application): LocationTracker {
        return DefaultLocationTracker(locationClient,application)
    }

    @Provides
    @Singleton
    fun provideGetLocationAndCityName(location: LocationTracker, geoCoding: GeoCoding): GetUserLocationAndCityNameUseCase {
        return GetUserLocationAndCityNameUseCase(location,geoCoding)
    }
    @Singleton
    @Provides
    fun getGeoCoding( @ApplicationContext context: Context): GeoCoding {
        return GeoCoding(Geocoder(context))
    }
}