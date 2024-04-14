package com.example.weatherapp.di

import DefaultLocationTracker
import android.app.Application
import android.content.Context
import android.location.Geocoder
import com.example.weatherapp.data.geocoder.GeoCoding
import com.example.weatherapp.data.remote.WeatherApi
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImp(weatherApi)
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
    fun provideLocationTracker(
        locationClient: FusedLocationProviderClient,
        application: Application
    ): LocationTracker {
        return DefaultLocationTracker(locationClient, application)
    }

    @Provides
    @Singleton
    fun provideGetLocationAndCityName(
        location: LocationTracker,
        geoCoding: GeoCoding
    ): GetUserLocationAndCityNameUseCase {
        return GetUserLocationAndCityNameUseCase(location, geoCoding)
    }

    @Singleton
    @Provides
    fun getGeoCoding(@ApplicationContext context: Context): GeoCoding {
        return GeoCoding(Geocoder(context))
    }
}