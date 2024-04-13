package com.example.weatherapp.domain.usecase

import com.example.weatherapp.util.connetivity.AppStatus
import javax.inject.Inject

class GetConnectivity @Inject constructor(
    var appStatus: AppStatus
) {
    fun isConnected(): Boolean{
        return appStatus.isNetworkAvailable()
    }
}